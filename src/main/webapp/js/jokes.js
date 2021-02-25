/**
 * @author Emil Elkjær Nielsen (cph-en93@cphbusiness.dk)
 */


document.getElementById("jokeid").value = '';

const URL = 'https://codergram.me/ca1/api/';

function getAllJokes(event) {
    const endpoint = 'jokes/all';

    console.log("Get all jokes")
    fetch(URL + endpoint)
            .then((resp) => resp.json())
            .then(function (data) {
                console.log(data)
                let rows = ''
                {
                    data.map(joke =>
                        rows += `<tr><td>${joke.type}</td><td>${joke.question}</td><td>${joke.answer}</td></tr>`
                    )
                }
                document.getElementById('tableContent').innerHTML = rows.replace(/\n/g, "<br>")
            })
            .catch(function (error) {
                console.log(error);
                document.getElementById("message").innerHTML = `<div className="alert alert-danger" role="alert">${error}</div>`
            });
}

function getJokeById(id) {
    const endpoint = `jokes/${id}`;
    console.log("Searching for joke with id: " + id)
    fetch(URL + endpoint)
            .then((resp) => resp.json())
            .then(function (data) {
                document.getElementById("message").innerHTML = `
<div className="alert alert-primary" role="alert">${data['question']}</div>
<div className="alert alert-success" role="alert">${data['answer']}</div>`;
                
                document.getElementById("jokeid").value = '';
            })
            .catch(function (error) {
                console.log(error);
            });
}

function getRandomJoke() {
    const endpoint = `jokes/random`;
    console.log("Getting random joke")
    fetch(URL + endpoint)
            .then((resp) => resp.json())
            .then(function (data) {
                document.getElementById("message").innerHTML = `
<div className="alert alert-primary" role="alert">${data['question']}</div>
<div className="alert alert-success" role="alert">${data['answer']}</div>`
            })
            .catch(function (error) {
                console.log(error);
            });
}

let jokeid = ""
document.getElementById("jokeid").addEventListener('input', function () {
    jokeid = this.value
})

document.getElementById("findjoke").addEventListener('click', function () {
    getJokeById(jokeid)
});

document.getElementById("randomjoke").addEventListener('click', function () {
    getRandomJoke()
})
window.addEventListener('load', function () {
    getAllJokes()
})