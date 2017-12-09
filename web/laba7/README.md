# Кроки інсталяції та стартування веб-сайту

#### 1. Потрібно скачати та встановити node.js в кореневу папку проекту.
     - для цього потрібно зайти в командний рядок кореневої папки та прописати там ```$ npm install```
#### 2. Потрібно створити файл server.js та налаштувати його:
        ```
        // server.js
        // BASE SETUP

        // call the packages we need
        var express    = require('express');        // call express
        var app        = express();                 // define our app using express
        var bodyParser = require('body-parser');

        // configure app to use bodyParser()
        // this will let us get the data from a POST
        app.use(bodyParser.urlencoded({ extended: true }));
        app.use(bodyParser.json());

        var port = process.env.PORT || 8080;        // set our port

        // ROUTES FOR OUR API
        
        var router = express.Router();              // get an instance of the express Router

        // test route to make sure everything is working (accessed at GET http://localhost:8080/api)
        router.get('/', function(req, res) {
            res.json({ message: 'hooray! welcome to our api!' });   
        });

        // more routes for our API will happen here

        // REGISTER OUR ROUTES
        // all of our routes will be prefixed with /api
        app.use('/api', router);

        // START THE SERVER
        
        app.listen(port);
        console.log('Magic happens on port ' + port); 
            ```
#### 3. Для запуску сервера в консолі прописати ```$ node server.js```   
#### 4. Перейти на <http://localhost:8080/api>         
