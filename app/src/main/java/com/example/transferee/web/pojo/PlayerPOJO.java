package com.example.transferee.web.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PlayerPOJO {

        @SerializedName("Player_ID")
        @Expose
        private Integer playerID;
        @SerializedName("Name")
        @Expose
        private String name;
        @SerializedName("Image_URL")
        @Expose
        private String imageURL;
        @SerializedName("Club_Name")
        @Expose
        private String clubName;
        @SerializedName("Club_URL")
        @Expose
        private String clubURL;

        /**
         * No args constructor for use in serialization
         *
         */
        public PlayerPOJO() {
        }

        /**
         *
         * @param imageURL
         * @param clubName
         * @param name
         * @param clubURL
         */
        public PlayerPOJO(Integer playerID, String name, String imageURL, String clubName, String clubURL) {
            super();
            this.playerID = playerID;
            this.name = name;
            this.imageURL = imageURL;
            this.clubName = clubName;
            this.clubURL = clubURL;
        }

        public Integer getPlayerID() {
        return playerID;
    }

        public void setPlayerID(Integer playerID) {
        this.playerID = playerID;
    }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getImageURL() {
            return imageURL;
        }

        public void setImageURL(String imageURL) {
            this.imageURL = imageURL;
        }

        public String getClubName() {
            return clubName;
        }

        public void setClubName(String clubName) {
            this.clubName = clubName;
        }

        public String getClubURL() {
            return clubURL;
        }

        public void setClubURL(String clubURL) {
            this.clubURL = clubURL;
        }

    }
