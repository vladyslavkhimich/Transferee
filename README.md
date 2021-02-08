# Transferee

Transferee is a native Android client-server app about football. Client-side is a native Android app build for Android 7.0+ with API Level 24. Server built as RESTful API with the help of Node.JS.

## Gallery
### Android client
![3 18](https://user-images.githubusercontent.com/59961299/107236734-a8ebd600-6a2e-11eb-9cd5-4917b5cdc0b9.jpg)
![3 19](https://user-images.githubusercontent.com/59961299/107236740-a9846c80-6a2e-11eb-9f4d-73187198771c.jpg)
![3 20](https://user-images.githubusercontent.com/59961299/107236741-aa1d0300-6a2e-11eb-893e-46fc6a5420ff.jpg)
![3 21](https://user-images.githubusercontent.com/59961299/107236742-aa1d0300-6a2e-11eb-972c-0f3ab15473e5.jpg)
![3 22](https://user-images.githubusercontent.com/59961299/107236744-aa1d0300-6a2e-11eb-80a5-7a4165f82be6.jpg)
![3 23](https://user-images.githubusercontent.com/59961299/107236746-aab59980-6a2e-11eb-986c-3ad527eb00a2.jpg)
![3 24](https://user-images.githubusercontent.com/59961299/107236751-aab59980-6a2e-11eb-9d26-acf86d53d608.jpg)

### Web admin panel
![Admin login page](https://user-images.githubusercontent.com/59961299/107237069-03853200-6a2f-11eb-88cf-ef06358869b4.jpg)
![Admin table page](https://user-images.githubusercontent.com/59961299/107237073-041dc880-6a2f-11eb-9968-2d2b5af23aa2.jpg)
![Asmin table creation page](https://user-images.githubusercontent.com/59961299/107237075-04b65f00-6a2f-11eb-967f-8bda9779347d.jpg)

## Architecture

You can look at whole project architecture on the diagram below.
![Transferee project architecture](https://user-images.githubusercontent.com/59961299/107238016-092f4780-6a30-11eb-8f9d-c468d136f697.png)

Talking about two parts of app - android app and server-side admin panel - they share similar architecture. In the case of mobile application it is MVVM (Model-View-ViewModel) pattern that is standard for flexible and expandable Android applications. For server-side admin panel that itself is web application was used MVC (Model-View-Controller) architecture pattern which is like a parent for MVVM.
![image003](https://user-images.githubusercontent.com/59961299/107220645-4937ff80-6a1b-11eb-80a0-ed6820c6b0ee.png)

## I have learnt
### Android
* Basics of Android apps development
* Creation of bottom navigation menu
* Creating Adapters for RecyclerViews
* Creating custom item layouts for RecyclerViews
* Connecting Android app with server with Retrofit2
* Loading server images to ImageView with Picasso
* Implementing MVVM pattern

### Node.JS
* Building RESTful API
* Connecting Sequelize models with AdminBro Panel
* Converting HTML to PDF

## Features
### Android
* Viewing top rated players list
* Viewing top market value players list
* Viewing latest transfers
* Searching players by name
* Adding players to favorites
* Viewing information about particular player
    * General information
    * Transfer market infromation
    * Last matches statistics
    * Career information

### Web admin panel
* Authorization to admin panel
* CRUD operations with database entities
* Filtering database entities
* Forms for uploading images for some database entities
* Download some reports in PDF format

## Built with
### Android
* Android API Level 24
* Retrofit2 REST Client
* Picasso Image Library
* Android Studio IDE

### Server
* Node.JS
* AdminBro Panel
* Express Framework
* Sequelize ORM
* Handlebars View Engine
* MySQL DBMS
* WebStorm IDE
