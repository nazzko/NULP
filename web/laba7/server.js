// BASE SETUP
// =============================================================================

// call the packages we need
var express = require('express');
var bodyParser = require('body-parser');
var app = express();
var morgan = require('morgan');
var cors = require('cors')

// configure app
app.use(cors());
app.use(morgan('dev')); // log requests to the console

// configure body parser
app.use(bodyParser.urlencoded({ extended: true }));
app.use(bodyParser.json());

var port = process.env.PORT || 8080; // set our port

// DATABASE SETUP
var mongoose = require('mongoose');
mongoose.connect('mongodb://Admin:yany504@ds133296.mlab.com:33296/lab10web11');

// Handle the connection event
var db = mongoose.connection;
db.on('error', console.error.bind(console, 'connection error:'));

db.once('open', function() {
    console.log("DB connection alive");
});

// Post models lives here
var Post = require('./app/models/post');


// ROUTES FOR OUR API
// =============================================================================

// create our router
var router = express.Router();

// middleware to use for all requests
router.use(function(req, res, next) {
    // do logging
    console.log('Something is happening.');
    next();
});

// test route to make sure everything is working (accessed at GET http://localhost:8080/api)
router.get('/', function(req, res) {
    res.json({ message: 'hooray! welcome to our api!' });
});

// on routes that end in /posts
// ----------------------------------------------------
router.route('/posts')

// create a post (accessed at POST http://localhost:8080/posts)
.post(function(req, res) {

    var post = new Post(); // create a new instance of the Post model
    post.title = req.body.title;
    post.longdescription = req.body.longdescription; // set the posts name (comes from the request)
    post.shortdescription = req.body.shortdescription;
    post.save(function(err) {
        if (err)
            res.send(err);

        res.json({ message: 'Post created!' });
    });


})

// get all the posts (accessed at GET http://localhost:8080/api/posts)
.get(function(req, res) {
    Post.find(function(err, posts) {
        if (err)
            res.send(err);

        res.json(posts);
    });
});

// on routes that end in /posts/:post_id
// ----------------------------------------------------
router.route('/posts/:post_id')

// get the post with that id
.get(function(req, res) {
    Post.findById(req.params.post_id, function(err, post) {
        if (err)
            res.send(err);
        res.json(post);
    });
})

// update the post with this id
.put(function(req, res) {
    Post.findById(req.params.post_id, function(err, post) {

        if (err)
            res.send(err);

        post.title = req.body.title;
        post.longdescription = req.body.longdescription;
        post.shortdescription = req.body.shortdescription
        post.save(function(err) {
            if (err)
                res.send(err);

            res.json({ message: 'Post updated!' });
        });

    });
})

// delete the post with this id
.delete(function(req, res) {
    Post.remove({
        _id: req.params.post_id
    }, function(err, post) {
        if (err)
            res.send(err);

        res.json({ message: 'Successfully deleted' });
    });
});


// REGISTER OUR ROUTES -------------------------------
app.use('/api', router);

// START THE SERVER
// =============================================================================
app.listen(port);
console.log('Magic happens on port ' + port);