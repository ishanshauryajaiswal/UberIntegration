function edit_row(no)
{
 document.getElementById("edit_button"+no).style.display="none";
 document.getElementById("save_button"+no).style.display="inline";
	
 var name=document.getElementById("name_row"+no);
 var country=document.getElementById("country_row"+no);
 var age=document.getElementById("age_row"+no);
 var zip=document.getElementById("zip_row"+no);
	
 var name_data=name.innerHTML;
 var country_data=country.innerHTML;
 var age_data=age.innerHTML;
 var zip_data=zip.innerHTML;
	
 name.innerHTML="<input type='text' id='name_text"+no+"' value='"+name_data+"'>";
 country.innerHTML="<input type='text' id='country_text"+no+"' value='"+country_data+"'>";
 age.innerHTML="<input type='text' id='age_text"+no+"' value='"+age_data+"'>";
 zip.innerHTML="<input type='text' id='zip_text"+no+"' value='"+zip_data+"'>";
}

function save_row(no)
{
 var name_val=document.getElementById("name_text"+no).value;
 var country_val=document.getElementById("country_text"+no).value;
 var age_val=document.getElementById("age_text"+no).value;
 var zip_val=document.getElementById("zip_text"+no).value;

 document.getElementById("name_row"+no).innerHTML=name_val;
 document.getElementById("country_row"+no).innerHTML=country_val;
 document.getElementById("age_row"+no).innerHTML=age_val;
 document.getElementById("zip_row"+no).innerHTML=zip_val;
 
 var obj=JSON.parse(localStorage.getItem(localStorage.key(no)));
 localStorage.setItem(obj.email,JSON.stringify({fullname:name_val,
										  email:obj.email,
										  pwd:obj.pwd,
										  phone:country_val,
										  address:age_val,
										  zip:zip_val}));
 

 document.getElementById("edit_button"+no).style.display="inline";
 document.getElementById("save_button"+no).style.display="none";
}

function delete_row(no)
{
	var obj=JSON.parse(localStorage.getItem(localStorage.key(no)));
	localStorage.removeItem(localStorage.key(no));
 document.getElementById("row"+no).outerHTML="";
 }


function add_row()
{
 var new_name=document.getElementById("new_name").value;
 var new_country=document.getElementById("new_country").value;
 var new_age=document.getElementById("new_age").value;
	
 var table=document.getElementById("dvTable");
 var table_len=table.getElementsByTagName("tr").length;
 //var table_len=localStorage.length;
 console.log(table_len);
 row = table.insertRow(table_len);//.outerHTML="<tr id='row"+table_len+"'><td id='name_row"+table_len+"'>"+new_name+"</td><td id='country_row"+table_len+"'>"+new_country+"</td><td id='age_row"+table_len+"'>"+new_age+"</td><td><input type='button' id='edit_button"+table_len+"' value='Edit' class='edit' onclick='edit_row("+table_len+")'> <input type='button' id='save_button"+table_len+"' value='Save' class='save' onclick='save_row("+table_len+")'> <input type='button' value='Delete' class='delete' onclick='delete_row("+table_len+")'></td></tr>";
 row.outerHTML="<tr id='row"+table_len+"'><td id='name_row"+table_len+"'>"+new_name+"</td><td id='country_row"+table_len+"'>"+new_country+"</td><td id='age_row"+table_len+"'>"+new_age+"</td><td><input type='button' id='edit_button"+table_len+"' value='Edit' class='edit' onclick='edit_row("+table_len+")'> <input type='button' id='save_button"+table_len+"' value='Save' class='save' onclick='save_row("+table_len+")'> <input type='button' value='Delete' class='delete' onclick='delete_row("+table_len+")'></td></tr>";

 document.getElementById("new_name").value="";
 document.getElementById("new_country").value="";
 document.getElementById("new_age").value="";
}

function openCity(evt, cityName) {
    // Declare all variables
    var i, tabcontent, tablinks;

    // Get all elements with class="tabcontent" and hide them
    tabcontent = document.getElementsByClassName("tabcontent");
    for (i = 0; i < tabcontent.length; i++) {
        tabcontent[i].style.display = "none";
    }

    // Get all elements with class="tablinks" and remove the class "active"
    tablinks = document.getElementsByClassName("tablinks");
    for (i = 0; i < tablinks.length; i++) {
        tablinks[i].className = tablinks[i].className.replace(" active", "");
    }

    // Show the current tab, and add an "active" class to the link that opened the tab
    document.getElementById(cityName).style.display = "block";
    evt.currentTarget.className += " active";
}

function noBack() { window.history.forward(); }

	var itr=0;
	var fullname=document.getElementById('fullname');
	var email=document.getElementById('email');
	var emailarray=[];
	emailarray[itr]=document.getElementById('email');
	var pwd=document.getElementById('pwd');
	var phone=document.getElementById('phone');
	var address=document.getElementById('address');
	var zip=document.getElementById('zip');
	//console.log(emailarray[0]);

function storeData(){
	for(var it=0;it<localStorage.length;it++){
		var JSONobj=localStorage.key(it);
		console.log(JSONobj);
		if(email.value==JSONobj){
			alert('Already registered with this Email Id');
			return;
		}
	}
	if(email.value!=""){
	localStorage.setItem(email.value,JSON.stringify({fullname:fullname.value,
										  email:email.value,
										  pwd:pwd.value,
										  phone:phone.value,
										  address:address.value,
										  zip:zip.value}));
	}
}

	

	function popstoreData(){
	var popfullname=document.getElementById('popfullname');
	var popemail=document.getElementById('popemail');
	var poppwd=document.getElementById('poppwd');
	var popphone=document.getElementById('popphone');
	var popaddress=document.getElementById('popaddress');
	var popzip=document.getElementById('popzip');
	//localStorage.setItem('fullname', fullname.value);
	
	
	if(popemail.value!=""){
		console.log(popemail.value);
	localStorage.setItem(popemail.value,JSON.stringify({fullname:popfullname.value,
										  email:popemail.value,
										  pwd:poppwd.value,
										  phone:popphone.value,
										  address:popaddress.value,
										  zip:popzip.value}));
	window.location="table.html";
	}
	}
function checkUser(){
	var username=document.getElementById('username');
	var pwduser=document.getElementById('pwd');
	
		console.log(localStorage.key(0));
	
	for(var i=0;i<localStorage.length;i++){
		if(localStorage.key(i)==username.value){
			var JSONobj=JSON.parse(localStorage.getItem(localStorage.key(i)));
			console.log(JSONobj.pwd);
			if(JSONobj.pwd==pwduser.value){
				//document.getElementById("head_greet").innerHTML="Welcome "+JSONobj.fullname;
				window.location= "table.html";
			}
			else{
				alert('Error');
			}
		}
	}
	return false;
}




function GenerateTable() {
    //Build an array containing Customer records.
    var customers = new Array();
    customers.push(["Name","Phone Number","Address","Zip","Actions"])
	for(var m=0;m<localStorage.length;m++){
		var JSONobj=JSON.parse(localStorage.getItem(localStorage.key(m)));
		customers.push([JSONobj.fullname,JSONobj.phone,JSONobj.address,JSONobj.zip]);
	}
 
    //Create a HTML Table element.
    var table = document.createElement("TABLE");
    table.border = "1";
 
    //Get the count of columns.
    var columnCount = customers[0].length;
 
    //Add the header row.
	 var row = table.insertRow(-1);

    for (var i = 0; i < columnCount; i++) {
        var headerCell = document.createElement("TH");
        headerCell.innerHTML = customers[0][i];
        row.appendChild(headerCell);
    }
 
    //Add the data rows.
    for (var i = 1; i < customers.length; i++) {
        row = table.insertRow(-1);
		var j=0;
		row.outerHTML = "<tr id='row"+(i-1)+"'><td id='name_row"+(i-1)+"'>"+customers[i][j]+"</td><td id='country_row"+(i-1)+"'>"+customers[i][j+1]+"</td><td id='age_row"+(i-1)+"'>"+customers[i][j+2]+"</td><td id='zip_row"+(i-1)+"'>"+customers[i][j+3]+"</td><td><input type='button' id='edit_button"+(i-1)+"' value='Edit' class='edit' onclick='edit_row("+(i-1)+")'> <input type='button' id='save_button"+(i-1)+"' value='Save' class='save' onclick='save_row("+(i-1)+")'> <input type='button' id='delete_button"+(i-1)+"' value='Delete' class='delete' onclick='delete_row("+(i-1)+")'></td></tr>";
	}
    var dvTable = document.getElementById("dvTable");
    dvTable.innerHTML = "";
    dvTable.appendChild(table);
}

//Function To Display Popup
function div_show() {
document.getElementById('abc').style.display = "block";
}
//Function to Hide Popup
function div_hide(){
document.getElementById('abc').style.display = "none";
}

function searchTable(){
	var data=document.getElementById("search");
	//console.log(data.value);
	for(var i=0;i<localStorage.length;i++){
		if(data.value==localStorage.key(i)){
			edit_row(i);
		}
	}
}