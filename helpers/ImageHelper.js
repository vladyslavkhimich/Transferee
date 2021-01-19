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

const checkIfClubImageExists = (imageName) => {
    let filePath = path.join(__dirname, 'public', 'images/club/' + imageName);
    filePath = removeHelperPartFromPath(filePath);
    try {
        return fs.existsSync(filePath);
    }
    catch(err) {
        console.log(err);
        return false;
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

const checkIfPlayerImageExists = (imageName) => {
    let filePath = path.join(__dirname, 'public', 'images/player/' + imageName);
    filePath = removeHelperPartFromPath(filePath);
    try {
        return fs.existsSync(filePath);
    }
    catch(err) {
        console.log(err);
        return false;
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

const checkIfCountryImageExists = (imageName) => {
    let filePath = path.join(__dirname, 'public', 'images/country/' + imageName);
    filePath = removeHelperPartFromPath(filePath);
    try {
        return fs.existsSync(filePath);
    }
    catch(err) {
        console.log(err);
        return false;
    }
};

function removeHelperPartFromPath(path) {
     return path.replace('\\helpers', '');
}

function getFileNameWithoutExtension(fileName) {
    return fileName.substring(0, fileName.indexOf('.'));
}

exports.getClubImagePath = getClubImagePath;
exports.getPlayerImagePath = getPlayerImagePath;
exports.getCountryImagePath = getCountryImagePath;

exports.getFileNameWithoutExtension = getFileNameWithoutExtension;

exports.checkIfPlayerImageExists = checkIfPlayerImageExists;
exports.checkIfClubImageExists = checkIfClubImageExists;
exports.checkIfCountryImageExists = checkIfCountryImageExists;