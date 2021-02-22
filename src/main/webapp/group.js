const server = 'https://codergram.me/ca1/api/';
const endpoint = 'api/groupmembers/';
const URL = server + endpoint;

function getStaticMembers(event){
  var member = ["Emil", "cph-en93", ["Keeping up with the kardasians", "Matador"]]
  document.getElementById("members").innerHTML = `<tr><td>${member[0]}</td><td>${member[1]}</td><td>${member[2]}</td></tr>`;
}



function getAllMembers(event){
  fetch(URL)
  .then((resp) => resp.json())
  .then(function(data) {
    return data.results.map(function(member) {
      document.getElementById("members").innerHTML = `<tr><td>${member.name}</td><td>${member.email}</td><td>${member.favseries}</td></tr>`;
    })
  })
  .catch(function(error) {
    console.log(error);
  });
}