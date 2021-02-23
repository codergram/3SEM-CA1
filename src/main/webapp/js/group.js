const server = 'https://codergram.me/ca1/api/';
const endpoint = 'groupmembers/all';
const URL = server + endpoint;

function getAllMembers(event){
  console.log("Get all members called")
  fetch(URL)
  .then((resp) => resp.json())
  .then(function(data) {
    console.log(data)
    return data.results.map(function(member) {
      document.getElementById("members").innerHTML = `<tr><td>${member.name}</td><td>${member.email}</td><td>${member.favseries}</td></tr>`;
    })
  })
  .catch(function(error) {
    console.log(error);
    document.getElementById("message").innerHTML = `<p style="color: #ff0000;">Error: ${error}</p>`
  });
}


// Document events
window.addEventListener('load', function() {
  getAllMembers()
})

document.getElementById("reload").addEventListener('click', getAllMembers);