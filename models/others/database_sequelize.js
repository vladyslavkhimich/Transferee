const {Sequelize, sequelize} = require('./sequelize');

//models

//club models
const clubFile = require('../club');
const clubTitleFile = require('../club_title');
const formerLeagueFile = require('../former_league');
const formerManagerFile = require('../former_manager');
const managerFile = require('../manager');

//league models
const leagueFile = require('../league');
const leagueSeasonFile = require('../league_season');
const seasonFile = require('../season');
const titleFile = require('../title');

//match models
const goalFile = require('../goal');
const mainSquadPlayerFile = require('../main_squad_player');
const matchFile = require('../match');
const matchCardFile = require('../match_card');
const matchPositionFile = require('../match_position');
const matchRatingFile = require('../match_player_rating');
const substitutionFile = require('../substitution');
const tourFile = require('../tour');
const typeOfCardFile = require('../type_of_card');

//others models
const countryFile = require('../country');

//player models
const formerClubFile = require('../former_club');
const generalPositionFile = require('../general_position');
const loanFile = require('../loan');
const playerFile = require('../player');
const playerContractFile = require('../player_contract');
const playerPositionFile = require('../player_position');
const playerPriceChangeFile = require('../player_price_change');
const transferFile = require('../transfer');


//models instances

//club models instances
const club = clubFile(sequelize, Sequelize);
const clubTitle = clubTitleFile(sequelize, Sequelize);
const formerLeague = formerLeagueFile(sequelize, Sequelize);
const formerManager = formerManagerFile(sequelize, Sequelize);
const manager = managerFile(sequelize, Sequelize);

//league models instances
const league = leagueFile(sequelize, Sequelize);
const leagueSeason = leagueSeasonFile(sequelize, Sequelize);
const season = seasonFile(sequelize, Sequelize);
const title = titleFile(sequelize, Sequelize);

//match models instances
const goal = goalFile(sequelize, Sequelize);
const mainSquadPlayer = mainSquadPlayerFile(sequelize, Sequelize);
const match = matchFile(sequelize, Sequelize);
const matchCard = matchCardFile(sequelize, Sequelize);
const matchPosition = matchPositionFile(sequelize, Sequelize);
const matchPlayerRating = matchRatingFile(sequelize, Sequelize);
const substitution = substitutionFile(sequelize, Sequelize);
const tour = tourFile(sequelize, Sequelize);
const typeOfCard = typeOfCardFile(sequelize, Sequelize);

//others models instances
const country = countryFile(sequelize, Sequelize);

//player models instances
const formerClub = formerClubFile(sequelize, Sequelize);
const generalPosition = generalPositionFile(sequelize, Sequelize);
const loan = loanFile(sequelize, Sequelize);
const player = playerFile(sequelize, Sequelize);
const playerContract = playerContractFile(sequelize, Sequelize);
const playerPosition = playerPositionFile(sequelize, Sequelize);
const playerPriceChange = playerPriceChangeFile(sequelize, Sequelize);
const transfer = transferFile(sequelize, Sequelize);


//relationships
//club relationships
club.belongsToMany(league, {through: formerLeague, foreignKey: 'Club_ID' });
league.belongsToMany(club, {through: formerLeague, foreignKey: 'League_ID'});
club.belongsToMany(manager, {through: formerManager, foreignKey: 'Club_ID'});
manager.belongsToMany(club, {through: formerManager, foreignKey: 'Manager_ID'});
club.belongsToMany(player, {through: formerClub, foreignKey: 'Club_ID'});
player.belongsToMany(club, {through: formerClub, foreignKey: 'Player_ID'});
club.hasMany(transfer, {foreignKey: 'Departure_Club_ID', sourceKey: 'Club_ID'});
transfer.belongsTo(club, {foreignKey: 'Departure_Club_ID', as: 'Departure_Club', targetKey: 'Club_ID'});
club.hasMany(transfer, {foreignKey: 'Joining_Club_ID', sourceKey: 'Club_ID'});
transfer.belongsTo(club, {foreignKey: 'Joining_Club_ID', as: 'Joining_Club', targetKey: 'Club_ID'});
club.hasMany(clubTitle, {foreignKey: 'Club_ID'});
clubTitle.belongsTo(club, {foreignKey: 'Club_ID'});
club.hasMany(mainSquadPlayer, {foreignKey: 'Club_ID'});
mainSquadPlayer.belongsTo(club, {foreignKey: 'Club_ID'});
club.hasMany(match, {foreignKey: 'Home_Club_ID', sourceKey: 'Club_ID'});
match.belongsTo(club, {foreignKey: 'Home_Club_ID', as: 'Home_Club', targetKey: 'Club_ID'});
club.hasMany(match, {foreignKey: 'Guest_Club_ID', sourceKey: 'Club_ID'});
match.belongsTo(club, {foreignKey: 'Guest_Club_ID', as: 'Guest_Club', targetKey: 'Club_ID'});

//club title relationships
//title relationship with club title
title.hasMany(clubTitle, {foreignKey: 'Title_ID'});
clubTitle.belongsTo(title, {foreignKey: 'Title_ID'});
//league season relationship with club title
clubTitle.belongsTo(leagueSeason, {foreignKey: 'League_Season_ID'});

//league relationships
country.hasMany(league, {foreignKey: 'Country_ID'});
league.belongsTo(country, {foreignKey: 'Country_ID'});
league.hasMany(leagueSeason, {foreignKey: 'League_ID'});
leagueSeason.belongsTo(league, {foreignKey: 'League_ID'});

//league season relationships
season.hasMany(leagueSeason, {foreignKey: 'Season_ID'});
leagueSeason.belongsTo(season, {foreignKey: 'Season_ID'});
leagueSeason.hasMany(tour, {foreignKey: 'League_Season_ID'});
tour.belongsTo(leagueSeason, {foreignKey: 'League_Season_ID'});

//player relationships
player.hasMany(transfer, {foreignKey: 'Player_ID'});
transfer.belongsTo(player, {foreignKey: 'Player_ID'});
player.hasMany(playerContract, {foreignKey: 'Player_ID'});
playerContract.belongsTo(player, {foreignKey: 'Player_ID'});
player.hasMany(playerPriceChange, {foreignKey: 'Player_ID'});
playerPriceChange.belongsTo(player, {foreignKey: 'Player_ID'});
player.hasMany(matchPosition, {foreignKey: 'Player_ID'});
matchPosition.belongsTo(player, {foreignKey: 'Player_ID'});
player.hasMany(goal, {foreignKey: 'Goalscorer_ID', sourceKey: 'Player_ID'});
goal.belongsTo(player, {foreignKey: 'Goalscorer_ID', as: 'Goalscorer', targetKey: 'Player_ID'});
player.hasMany(goal, {foreignKey: 'Assistant_ID', sourceKey: 'Player_ID'});
goal.belongsTo(player, {foreignKey: 'Assistant_ID', as: 'Assistant', targetKey: 'Player_ID'});
player.hasMany(matchCard, {foreignKey: 'Player_ID'});
matchCard.belongsTo(player, {foreignKey: 'Player_ID'});
player.hasMany(mainSquadPlayer, {foreignKey: 'Player_ID'});
mainSquadPlayer.belongsTo(player, {foreignKey: 'Player_ID'});
player.hasMany(matchPlayerRating, {foreignKey: 'Player_ID'});
matchPlayerRating.belongsTo(player, {foreignKey: 'Player_ID'});
player.hasMany(substitution, {foreignKey: 'Substituted_Player_ID', sourceKey: 'Player_ID'});
substitution.belongsTo(player, {foreignKey: 'Substituted_Player_ID', as: 'Substituted_Player', targetKey: 'Player_ID'});
player.hasMany(substitution, {foreignKey: 'Substitute_Player_ID', sourceKey: 'Player_ID'});
substitution.belongsTo(player, {foreignKey: 'Substitute_Player_ID', as: 'Substitute_Player', targetKey: 'Player_ID'});
country.hasMany(player, {foreignKey: 'Country_ID'});
player.belongsTo(country, {foreignKey: 'Country_ID'});

//match relationships
tour.hasMany(match, {foreignKey: 'Tour_ID'});
match.belongsTo(tour, {foreignKey: 'Tour_ID'});
match.hasMany(mainSquadPlayer, {foreignKey: 'Match_ID'});
mainSquadPlayer.belongsTo(match, {foreignKey: 'Match_ID'});
match.hasMany(matchPlayerRating, {foreignKey: 'Match_ID'});
matchPlayerRating.belongsTo(match, {foreignKey: 'Match_ID'});
match.hasMany(substitution, {foreignKey: 'Match_ID'});
substitution.belongsTo(match, {foreignKey: 'Match_ID'});
match.hasMany(matchCard, {foreignKey: 'Match_ID'});
matchCard.belongsTo(match, {foreignKey: 'Match_ID'});
match.hasMany(goal, {foreignKey: 'Match_ID'});
goal.belongsTo(match, {foreignKey: 'Match_ID'});
match.hasMany(matchPosition, {foreignKey: 'Match_ID'});
matchPosition.belongsTo(match, {foreignKey: 'Match_ID'});

//match card relationships
typeOfCard.hasMany(matchCard, {foreignKey: 'Type_Of_Card_ID'});
matchCard.belongsTo(typeOfCard, {foreignKey: 'Type_Of_Card_ID'});

//match position and general position relationships
generalPosition.hasMany(playerPosition, {foreignKey: 'General_Position_ID'});
playerPosition.belongsTo(generalPosition, {foreignKey: 'General_Position_ID'});
playerPosition.hasMany(matchPosition, {foreignKey: 'Player_Position_ID'});
matchPosition.belongsTo(playerPosition, {foreignKey: 'Player_Position_ID'});

//loan relationships
transfer.hasOne(loan, {foreignKey: 'Transfer_ID'});
loan.belongsTo(transfer, {foreignKey: 'Transfer_ID'});

//database syncing
sequelize.sync().then(result => console.log(result)).catch(err => console.log(err));