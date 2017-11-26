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
    len = localStorage.length+1;
    for (var k=1; k<len; k++){
        $("#comment_list").append("<li><div class='col-sm-1'><div class='thumbnail'><img class='img-responsive user-photo' src='https://ssl.gstatic.com/accounts/ui/avatar_2x.png'></div></div><div class='col-sm-5'><div class='panel panel-default'><div class='panel-heading'><strong>myusername</strong> <span class='text-muted'></span></div><div class='panel-body'></div></div></div></li>");

        review =JSON.parse(localStorage.getItem(k));
        console.log(review[0].time);

        $('#comment_list li:last .text-muted').append(review[0].time);
        $('#comment_list li:last .panel-body').append(review[0].message);

        localStorage.removeItem(k);
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
            i++;
            var list = [];
            list.push({"message":$('#comment').val(), "time":date.toDateString()});
            localStorage.setItem(i, JSON.stringify(list));
            document.getElementById('comment').value='';
        }
    
});