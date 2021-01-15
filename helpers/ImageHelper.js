const getClubImagePath = (imageName) => {
    return '/images/club/' + imageName + '.png';
};

const getPlayerImagePath = (imageName) => {
    return '/images/player/' + imageName + '.png';
};

const getCountryImagePath = (imageName) => {
    return '/images/country/' + imageName + '.png';
};

exports.getClubImagePath = getClubImagePath;
exports.getPlayerImagePath = getPlayerImagePath;
exports.getCountryImagePath = getCountryImagePath;