const db = require('../models');
let pdf = require('html-pdf');
let options = {format: 'Letter'};

exports.getTopRatedPlayersPage =(request, response) => {
    db.Player.findAll({include: [{model: db.Match_Player_Rating, order: [['Match_Rating_ID', 'DESC']], limit: 10}, {model: db.Club}], order: [[db.Club, db.Former_Club, 'Date_Of_Joining', 'DESC']]}).then(players => {
        players = players.filter((player) => {
            return player.Match_Player_Ratings.length > 0;
        });
        players.forEach(player => {
            player.setDataValue('Average_Rating', calculatePlayerAverageRating(player.Match_Player_Ratings));
        });
        players.sort((a, b) => b['Average_Rating'] - a['Average_Rating']);
        let playersResponse = players.slice(0, 10).map((player) => ({Player_ID: player.Player_ID, Name: player.Name, Club_Name: player.Clubs[0].Name, Average_Rating: player.getDataValue('Average_Rating')}));
        response.render('topratedplayersreport', {
           playersResponse: playersResponse,
        }, (err, html) => {
            pdf.create(html, options).toFile('./public/pdfs/toprated.pdf', function (err, result) {
                if (err)
                    console.log(err);
                response.render('topratedplayerspage', {playersResponse: playersResponse});
            });

        });
        console.log('Players queried at ' + new Date());
    });
};

function calculatePlayerAverageRating(matchRatings) {
    let ratingsSum = 0.0;
    matchRatings.forEach(matchRating => {
        ratingsSum += matchRating.Match_Rating;
    });
    return ratingsSum / matchRatings.length;
}

exports.getTopMarketValuePlayersPage = (req, res) => {
    db.Player.findAll({include: [{model: db.Player_Price_Change, order: [['Player_Price_Change_ID', 'DESC']], limit: 1}, {model: db.Club}], order: [[db.Club, db.Former_Club, 'Date_Of_Joining', 'DESC']]}).then(players => {
        players = players.filter((player) => {
            return player.Player_Price_Changes.length > 0;
        });
        players.sort((a, b) => b.Player_Price_Changes[0].Market_Price - a.Player_Price_Changes[0].Market_Price);
        let playersResponse = players.slice(0,10).map((player) => ({Player_ID: player.Player_ID, Name: player.Name, Club_Name: player.Clubs[0].Name, Market_Price: player.Player_Price_Changes[0].Market_Price}));
        res.render('topmarketplayersreport', {playersResponse: playersResponse}, (err, html) => {
            pdf.create(html, options).toFile('./public/pdfs/topmarket.pdf', (err, result) => {
                if (err)
                    console.log(err);
                res.render('topmarketplayerspage', {playersResponse: playersResponse});
            })
        });
        console.log('Top market value players queried at ' + new Date());
    });
};