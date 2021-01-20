const AdminBro = require('admin-bro');

const db = require('../models');

const adminBroSidebarGroups = {
    club: {
        name: 'Club Management',
        icon: 'Events'
    },
    league: {
        name: 'League Management',
        icon: 'Trophy'
    },
    match: {
        name: 'Match Management',
        icon: 'Soccer'
    },
    others: {
        name: 'Others Management',
        icon: 'Table'
    },
    player: {
        name: 'Player Management',
        icon: 'User'
    },
    management: {
        name: 'Panel management',
        icon: 'User admin'
    }
};

const adminBro = new AdminBro({
    rootPath: '/admin',
    loginPath: '/admin/login',
    resources: [
        // club resources
        {
            resource: db.Club,
            options: {
                parent: adminBroSidebarGroups.club
            }
        },
        {
            resource: db.Club_Title,
            options: {
                parent: adminBroSidebarGroups.club
            }
        },
        {
            resource: db.Former_League,
            options: {
                parent: adminBroSidebarGroups.club
            }
        },
        {
            resource: db.Former_Manager,
            options: {
                parent: adminBroSidebarGroups.club
            }
        },
        {
            resource: db.Manager,
            options: {
                parent: adminBroSidebarGroups.club
            }
        },
        // end of club resources

        // league resources
        {
            resource: db.League,
            options: {
                parent: adminBroSidebarGroups.league
            }
        },
        {
            resource: db.League_Season,
            options: {
                parent: adminBroSidebarGroups.league
            }
        },
        {
            resource: db.Season,
            options: {
                parent: adminBroSidebarGroups.league
            }
        },
        {
            resource: db.Title,
            options: {
                parent: adminBroSidebarGroups.league
            }
        },
        // end of league resources

        // match resources
        {
            resource: db.Goal,
            options: {
                parent: adminBroSidebarGroups.match
            }
        },
        {
            resource: db.Main_Squad_Player,
            options: {
                parent: adminBroSidebarGroups.match
            }
        },
        {
            resource: db.Match,
            options: {
                parent: adminBroSidebarGroups.match
            }
        },
        {
            resource: db.Match_Card,
            options: {
                parent: adminBroSidebarGroups.match
            }
        },
        {
            resource: db.Match_Position,
            options: {
                parent: adminBroSidebarGroups.match
            }
        },
        {
            resource: db.Match_Player_Rating,
            options: {
                parent: adminBroSidebarGroups.match
            }
        },
        {
            resource: db.Substitution,
            options: {
                parent: adminBroSidebarGroups.match
            }
        },
        {
            resource: db.Tour,
            options: {
                parent: adminBroSidebarGroups.match
            }
        },
        {
            resource: db.Type_Of_Card,
            options: {
                parent: adminBroSidebarGroups.match
            }
        },
        // end of match resources

        // others resources
        {
            resource: db.Country,
            options: {
                parent: adminBroSidebarGroups.others
            }
        },
        // end of others resources

        // player resources
        {
            resource: db.Former_Club,
            options: {
                parent: adminBroSidebarGroups.player
            }
        },
        {
            resource: db.General_Position,
            options: {
                parent: adminBroSidebarGroups.player
            }
        },
        {
            resource: db.Loan,
            options: {
                parent: adminBroSidebarGroups.player
            }
        },
        {
            resource: db.Player,
            options: {
                parent: adminBroSidebarGroups.player
            }
        },
        {
            resource: db.Player_Contract,
            options: {
                parent: adminBroSidebarGroups.player
            }
        },
        {
            resource: db.Player_Position,
            options: {
                parent: adminBroSidebarGroups.player
            }
        },
        {
            resource: db.Player_Price_Change,
            options: {
                parent: adminBroSidebarGroups.player
            }
        },
        {
            resource: db.Transfer,
            options: {
                parent: adminBroSidebarGroups.player
            }
        },
        // end of player resources
        //start of management resources
        {
            resource: db.User,
            options: {
                parent: adminBroSidebarGroups.management,
                properties: {
                    encryptedPassword: {
                        isVisible: false,
                    },
                    password: {
                        type: 'password',
                        isVisible: {
                            show: false,
                            edit: true,
                            list: false,
                            filter: false,
                        }
                    }
                }
            },
        }
    ],
    branding: {
        companyName: 'Transferee',
        softwareBrothers: false
    }
});

module.exports = adminBro;