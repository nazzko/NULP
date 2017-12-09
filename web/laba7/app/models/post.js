var mongoose     = require('mongoose');
var Schema       = mongoose.Schema;

var PostSchema   = new Schema({
	title: String,
    shortdescription: String,
    longdescription: String
},{
    versionKey: false // You should be aware of the outcome after set to false
});


module.exports = mongoose.model('Post', PostSchema);