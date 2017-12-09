$.ajax({
    url: 'http://localhost:8080/api/posts',
    type: "get",
    dataType: "json",

    success: function(data) {
        drawTable(data);
    }
});

function drawTable(data) {
    for (var i = 0; i < data.length; i++) {
        drawRow(data[i]);
    }
}

function drawRow(rowData) {
    var row = $("<div class='col-sm-6 col-md-4'>")
    $("#news_list").append(row);
    Addition = "<div class='clearfix visible-sm-block'></div>"
    row.append($("<div class='news-item'>" + 
          "<div class='newsphoto'>" +
           "<img src='images/1.jpg' width='100%' height='100%'></div>" +
            "<div class='body-news'>" +
             "<h4 class='title'>"+rowData.title+"</h4>" +
             "<hr>" +
             "<p class='long_description'>"+rowData.lng_desc+"</p></div></div>"));
    row.append($(Addition));
                 

}

                 