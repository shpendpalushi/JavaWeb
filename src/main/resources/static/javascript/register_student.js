window.addEventListener("load", start, false);
function start(){
	 var submit = document.getElementById('submitButton');
    submit.addEventListener("click", function (event)
    {
    	var s = document.getElementById('sistemi');
        var sistemi = s.options[s.selectedIndex].value
        console.log(sistemi)
        var v = document.getElementById('viti')
        var viti = v.options[v.selectedIndex].value;
        console.log(viti)
       
        var error = '<div class="alert alert-danger" role="alert">Nuk besoj ta keni ne rregull vitin.</div>'
        if((sistemi == 'Master_Shkencor' || sistemi == 'Master_Profesional') && viti === '3' )
        {
            document.getElementById("error_log").innerHTML += error;   
            event.preventDefault();
            event.stopPropagation();
        }
        console.log(viti)
        console.log(sistemi)
    });
}

