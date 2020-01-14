// var active = false;
// window.addEventListener("load", start, false)
// function start(){
//     document
//     .getElementById("shtoTeze")
//     .addEventListener("click", shtoTeze, false);
// }

// function shtoTeze(){
//     if(!active){
//         document
//         .getElementById("secondColumn")
//         .innerHTML += subjectContent();

//     document
//     .getElementById("minus")
//     .addEventListener("click", minusClicked, false);
//     }

//     document
//     .getElementById("plus")
//     .addEventListener("click", plusClicked, false);
// }

// function subjectContent(){
//     return '<div class="container">'+
//     '<div class="card" style="margin-top: 2em">'+
//         '<div class="card-header">Shtoni testin tuaj ketu, duhet t\'i kete minimumi 4 pyetje</div>'+
//             '<div class="body">'+
//                 '<form th:action="@{/teacher/1" th:object="${thiesis}" method="post">'+
//                     '<input type="hidden" th:name="${_csrf.parameterName}" th:value="${_csrf.token}" required/>'+
//                     '<div class="form-group row" id="course_id">'+
//                         '<label for="pyetja_1" class="col-12 col-sm-4 col-form-label">Pyetje:</label>'+
//                         '<div class=" col-12 col-sm-8">'+ 	
//                             '<input type="text" class="form-control" placeholder="Emri i Lendes..." th:field="*{course.name}" required/><br/>'+
//                         '</div>'+						
//                     '</div>'+
//                     '<div class="form-group row" id="pyetja_1">'+
//                         '<label for="pyetja_1" class="col-12 col-sm-4 col-form-label">Pyetje:</label>'+
//                         '<div class=" col-12 col-sm-8">'+ 	
//                             '<input type="text" class="form-control" placeholder="Shkruaj pyetjen ketu..." th:field="*{questions.question}" required/><br/>'+
//                         '</div>'+						
//                     '</div>'+
//                     '<div id="body"></div>'+
//                     '<div class="row col-12 pb-2">'+
//                         '<div class="float-right"><i id="plus" class="fas fa-plus-circle fa-lg"></i></div>'+
//                         '<div class="float-right"><i id="minus" class="fas fa-minus-circle fa-lg"></i></div>'+
//                      '</div>'+
//                     '<div class="row col-12">'+
//                         '<div><button type="submit" class="btn btn-success ">Create account</button></div>'+
//                      '</div>'+
//                 '</form>'+
//             '</div>'+
//         '</div>'+
//     '</div>'
// }

// var i = 1;
// function minusClicked(){
//     console.log("minus clicked")
//     if(i>0){
//         document.getElementById("pyetja_"+i).remove();
//         i--;
//         console.log(i);
//     }
// }

// function plusClicked(){
//     console.log("plus clicked")
//     var formBody = document.getElementById("body");
//     i++;
//     formBody.innerHTML += 
//     '<div class="form-group row" id="pyetja_"'+i+'>'+
//         '<label for="pyetja_1" class="col-12 col-sm-4 col-form-label">Pyetje:</label>'+
//         '<div class=" col-12 col-sm-8">'+ 	
//         '<input type="text" class="form-control" placeholder="Shkruaj pyetjen ketu..." th:field="*{questions.question}" required/><br/>'+
//         '</div>'+
//     '</div>';
//     if(i>10){
//         formBody.innerHTML += '<div class="alert alert-danger" role="alert">Kujdes, ju po ia merrni shpirtin studenteve.</div>';
//     }

// }

// function addQuestion(){

// }