var useLocalStorage = false;

var indexedDB = window.indexedDB || window.mozIndexedDB || window.webkitIndexedDB || window.msIndexedDB;
IDBTransaction  = window.IDBTransaction || window.webkitIDBTransaction || window.msIDBTransaction;

var request = window.indexedDB.open('db', 3 );

request.onerror = function (event) {
    alert("Database error: " + event.target.errorCode);
};

request.onsuccess = function(event){
    useLocalStorage = false;
    db = event.target.result;
};

request.onupgradeneeded = function(event){
    var db = event.target.result;
    var news = db.createObjectStore("news", {autoIncrement: true});
    var comments = db.createObjectStore("comments", {autoIncrement: true});
}; 