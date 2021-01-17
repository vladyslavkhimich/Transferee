const path = require('path');
const fs = require('fs');

const getClubImagePath = (imageName) => {
    let filePath = path.join(__dirname, 'public', 'images/club/' + imageName + '.png');
    filePath = removeHelperPartFromPath(filePath);
    try {
        if (fs.existsSync(filePath))
            return '/images/club/' + imageName + '.png';
        return '/images/club/club.png';
    }
    catch (err) {
        console.log(err);
    }

};

const getPlayerImagePath = (imageName) => {
    let filePath = path.join(__dirname, 'public', 'images/player/' + imageName + '.png');
    filePath = removeHelperPartFromPath(filePath);
    try {
        if (fs.existsSync(filePath))
            return '/images/player/' + imageName + '.png';
        return '/images/player/player.png';
    }
    catch(err) {
        console.log(err);
    }
};

const getCountryImagePath = (imageName) => {
    let filePath = path.join(__dirname, 'public', 'images/country/' + imageName + '.png');
    filePath = removeHelperPartFromPath(filePath);
    try {
        if (fs.existsSync(filePath))
            return '/images/country/' + imageName + '.png';
        return '/images/country/world.png';
    }
    catch(err) {
        console.log(err);
    }

};

function removeHelperPartFromPath(path) {
     return path.replace('\\helpers', '');
}

exports.getClubImagePath = getClubImagePath;
exports.getPlayerImagePath = getPlayerImagePath;
exports.getCountryImagePath = getCountryImagePath;