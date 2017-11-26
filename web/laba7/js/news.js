var i = 0;

//validation form of news

$("#button").click(function validateForm() {
    var title = document.getElementById("title").value.trim();
    var lng_desc = document.getElementById("long_description").value.trim();
    if (title == "") {
        alert("Ви не додали заголовок");
        return false;
    }
    else if(lng_desc == "") {
        alert("Ви не заповнили повний опис");
        return false;
    }
    
     if (title !== "" && lng_desc !== "" && isOnline()){ 
            var title = document.getElementById("title").value;
            var lng_desc = document.getElementById("long_description").value;
            var parentElem = document.getElementById('news_list'); 
            var out = document.createElement('div');
            out.id = 'news';
            out.innerHTML = "<div class='col-sm-6 col-md-4'>" +
         "<div class='news-item'>" + 
          "<div class='newsphoto'>" +
           "<img src='images/1.jpg' width='100%' height='100%'></div>" +
            "<div class='body-news'>" +
             "<h4 class='title'>"+title+"</h4>" +
             "<hr>" +
             "<p class='long_description'>"+lng_desc+"</p></div></div></div>";

            parentElem.appendChild(out);
 
        document.getElementById('title').value='';
        document.getElementById('long_description').value='';
        alert("Опубліковано!")
    }
    else {
        i++;
         var list = [];
         list.push({"title":$('#title').val(),
            "long_description":$('#long_description').val()});
         localStorage.setItem(i, JSON.stringify(list));
         $('#long_description').val('');
         $('#title').val('');
    }
});

 function isOnline() {
     return window.navigator.onLine;
 }

window.addEventListener('load', function() {

    function updateOnlineStatus(event) {
        if(isOnline()){
            ReadOflineNews();
        }
    }
    window.addEventListener('online',  updateOnlineStatus);
    window.addEventListener('offline', updateOnlineStatus);
});

function ReadOflineNews() {
    len = localStorage.length + 1;
    for (var k = 1; k < len; k++) {
        $("#news_list").prepend("<div class='col-sm-6 col-md-4'>" +
         "<div class='news-item'>" + 
          "<div class='newsphoto'>" +
           "<img src='images/1.jpg' width='100%' height='100%'></div>" +
            "<div class='body-news'>" +
             "<h4 class='title'></h4>" +
             "<hr>" +
             "<p class='long_description'></p></div></div></div>");

        news = JSON.parse(localStorage.getItem(k));
        console.log(news[0].title);
        console.log(news[0].long_description);

        $('#news_list .col-sm-6:first .title').append(news[0].title);
        $('#news_list .col-sm-6:first .long_description').append(news[0].long_description);

        localStorage.removeItem(k);
    }
}

