const URL = 'https://codergram.me/ca1/api/';

function getAllMembers(event){
  const endpoint = 'groupmembers/all';

  console.log("Get all members called")
  fetch(URL+endpoint)
  .then((resp) => resp.json())
  .then(function(data) {
    console.log(data)
    let rows = ''
    {data.map(member =>
        rows += `<tr><td>${member.name}</td><td>${member.email.slice(0,member.email.indexOf('@'))}</td><td>${member.favseries.join(', ')}</td></tr>`
    )}
    document.getElementById('members').innerHTML = rows
  })
  .catch(function(error) {
    console.log(error);
    document.getElementById("message").innerHTML = `<p style="color: #ff0000;">Error: ${error}</p>`
  });
}

function getMemberWork(name){
  const endpoint = `groupmembers/madeby/${name}`;
  console.log("Getting data for " + name)
  fetch(URL+endpoint)
  .then((resp) => resp.json())
  .then(function(data) {
    {data.map(member =>
        document.getElementById(name).innerHTML = member.msg
    )}
  })
  .catch(function(error) {
    console.log(error);
  });
}


// Document events depending on which page
if(document.getElementById("membertable")){
  console.log("On index page. Adding eventlisteners")
  window.addEventListener('load', function() { getAllMembers() })
  document.getElementById("reload").addEventListener('click', getAllMembers);
} else if(document.getElementById("grouppage")) {
  console.log("On group page. adding eventlisterns")
  window.addEventListener('load', function() {
    getMemberWork("all")
    getMemberWork("emil")
    getMemberWork("arik")
    getMemberWork("jacob")

  })
} else {
  console.log("Not on index.")
  alert("Element does not exist");
}