window.addEventListener('load', function() {

    function updateOnlineStatus(event) {
        if(isOnline()){
            ReadOflineReview();
        }
    }
    window.addEventListener('online',  updateOnlineStatus);
    window.addEventListener('offline', updateOnlineStatus);
});


var i = 0;

function isOnline() {
    return window.navigator.onLine;
}

function ReadOflineReview() {
    if (useLocalStorage) {
    len = localStorage.length+1;
    for (var k=1; k<len; k++){
        $("#comment_list").append("<li><div class='col-sm-1'><div class='thumbnail'><img class='img-responsive user-photo' src='https://ssl.gstatic.com/accounts/ui/avatar_2x.png'></div></div><div class='col-sm-5'><div class='panel panel-default'><div class='panel-heading'><strong>myusername</strong> <span class='text-muted'></span></div><div class='panel-body'></div></div></div></li>");

        review =JSON.parse(localStorage.getItem(k));
        console.log(review[0].time);

        $('#comment_list li:last .text-muted').append(review[0].time);
        $('#comment_list li:last .panel-body').append(review[0].message);

        localStorage.removeItem(k);
    }
    } else {
        var transaction = db.transaction(["comments"], "readonly");
        var store = transaction.objectStore("comments");
            
            store.openCursor().onsuccess = function (event) {
            var cursor = event.target.result;
            if (cursor) {
                cursor.continue();
                $("#comment_list").append("<li><div class='col-sm-1'><div class='thumbnail'><img class='img-responsive user-photo' src='https://ssl.gstatic.com/accounts/ui/avatar_2x.png'></div></div><div class='col-sm-5'><div class='panel panel-default'><div class='panel-heading'><strong>myusername</strong> <span class='text-muted'></span></div><div class='panel-body'></div></div></div></li>");
                
                $('#comment_list li:last .text-muted').append(cursor.value.time);
                $('#comment_list li:last .panel-body').append(cursor.value.message);
    }
}
    }
}

//validation form of comments

$("#addcomment").click(function validateCommentForm() {
    var comment = document.getElementById("comment").value;
    if (comment == "") {
        alert("Ви не додали коментар");
        return false;
    }
    
        var date = new Date;
    
     if (comment !== "" && isOnline()){ 
            var text = document.getElementById('comment').value;
            var parentElem = document.getElementById('comment_list'); 
            var out = document.createElement('div');
            out.id = 'reviews';
            out.innerHTML = "<li><div class='col-sm-1'><div class='thumbnail'><img class='img-responsive user-photo' src='https://ssl.gstatic.com/accounts/ui/avatar_2x.png'></div></div><div class='col-sm-5'><div class='panel panel-default'><div class='panel-heading'><strong>myusername</strong> <span class='text-muted'>" + date.toDateString() + "</span></div><div class='panel-body'>" + text + "</div></div></div></li>";

            parentElem.appendChild(out);

            alert("Коментар додано") 
            document.getElementById('comment').value='';
        } 
        else {
            if (useLocalStorage){
            i++;
            var list = [];
            list.push({
                "message":$('#comment').val(), 
                "time": date.toDateString()
            });
                
            localStorage.setItem(i, JSON.stringify(list));
                
            } else {
                var transaction = db.transaction(["comments"], "readwrite");
                var store = transaction.objectStore("comments");
                var comment = {
                    message: $('#comment').val(),
                    time: date.toDateString()
                };
                store.add(comment);
            }
            document.getElementById('comment').value='';
        }
    
});