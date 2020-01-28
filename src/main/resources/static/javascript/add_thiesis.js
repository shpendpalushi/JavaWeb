window.addEventListener("load", start);
var i=1;
var question = '<div class="form-group row">'+
                    '<label for="name" class="col-12 col-sm-4 col-form-label">Question:</label>'+
                    '<div class=" col-12 col-sm-8">'+
                        '<input type="text" class="form-control" placeholder="Enter question here..."  required/><br/>'+
                    '</div>'+
                '</div>';

function start(){
    var plus = document.getElementById("plus");
    var minus = document.getElementById("minus");


    plus.addEventListener("click", add);
    minus.addEventListener("click", remove);
}


function add(){
    var addQuestion = document.getElementById("question_body");
    addQuestion.innerHTML += question;
    i++;
    console.log(i);
}

function remove(){
    var removeQuestion = document.getElementById("question_body");
    if(i>0){
        console.log(removeQuestion.lastChild.nodeName);
        removeQuestion.removeChild(removeQuestion.lastChild);
        i--;
        console.log(i);
    }
    
}