'use strict';

var express = require('express');
var bodyParser = require('body-parser');
var mongoose = require('mongoose');
var Schema = mongoose.Schema;
mongoose.connect('mongodb://localhost:27017');
//var ctc = require('./model/ctc');
var app = express();
//app.use(express.errorHandler());

app.use(bodyParser.urlencoded({extended: true}));
app.use(bodyParser.json());
//data base schema 
var databaseSchema = new mongoose.Schema({
  name: String,
  type:	String,
  lattitude: String,
  longitude: String,
  date: Date
});

//creating a dabase model
var db=mongoose.model('database',databaseSchema); 

app.get('/current',function(req,res){
	mongoose.model('database').find(function(err,database){ //accessing the database model using the .find() method
		//console.log(req);
		res.send(database);	
		});
	//res.send("/GET to /register");
	});

app.post('/register', function(req, res){
    //for inserting data into database
    db.collection.insert(req.body, {}, function(){
		console.log(req.body);
		});
    res.send('POST to /register');
	});
//update is use for location update
app.post('/update',function(req,res){
		console.log(req.body);
		res.send('POST to /update')
	});
//panice is use for panic button	
app.post('/panic',function(req,res){
	console.log(req.body);
	res.send('POST to /panic');
	});
//complain is use for complain button	
app.post('/complain',function(req,res){
	console.log(req.body);
	res.send('POST to/complain');
	});
//tracking 
app.post('/tracking',function(req,res){
		console.log(req.body);
		res.send('POST to /tracking')
	});
	

	
var server = app.listen(3002, function() {
    var host = server.address().address
    var port = server.address().port

    console.log("Server running at http://%s:%s", host, port);
});
