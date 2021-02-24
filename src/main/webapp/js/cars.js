/* global fetch */

function findCarById() {

    //event.preventDefault();
    let id = document.getElementById("car_id").value;
    //console.log(id);

    fetch('api/cars/' + id)
            .then(res => res.json())
            .then(data => {
                //console.log("data", data);
                htmlTag = `<tr><td>${data.id}</td><td>${data.brand}</td><td>${data.model}</td><td>${data.year}</td><td>${data.price}</td></tr>`;
                //console.log(htmlTag);
                document.getElementById('tableContent').innerHTML = htmlTag;

                //Clear fields
                clearData();
                
            }).catch(function (error) {
        console.log(error);
        document.getElementById("message").innerHTML = `<p style="color: #ff0000;">Error: ${error}</p>`;
    });
}
;

document.getElementById("findCarPrice").onclick = () => {
    let price = document.getElementById("car_price").value;
    //console.log(price);
    fetch('api/cars/all')
            .then(res => res.json())
            .then(data => {
                let filteredData = data.filter(currentElement => currentElement.price < price);
                //console.log("data", data);
                const htmlTagArray = filteredData.map(currentElement => {
                    //console.log(currentElement);
                    const row = `<tr><td>${currentElement.id}</td><td>${currentElement.brand}</td><td>${currentElement.model}</td><td>${currentElement.year}</td><td>${currentElement.price}</td></tr>`;
                    return row;
                });
                htmlTag = htmlTagArray.join("");
                //console.log(htmlTag);
                document.getElementById('tableContent').innerHTML = htmlTag;

                //Clear fields
                clearData();
                
            }).catch(function (error) {
        console.log(error);
        document.getElementById("message").innerHTML = `<p style="color: #ff0000;">Error: ${error}</p>`;
    });
};


document.getElementById("findCarBrand").onclick = () => {
    let brand = document.getElementById("car_brand").value;
    console.log(brand);
    fetch('api/cars/brand/' + brand)
            .then(res => res.json())
            .then(data => {
                //console.log("data", data);
                const htmlTagArray = data.map(currentElement => {
                    //console.log(currentElement);
                    const row = `<tr><td>${currentElement.id}</td><td>${currentElement.brand}</td><td>${currentElement.model}</td><td>${currentElement.year}</td><td>${currentElement.price}</td></tr>`;
                    return row;
                });
                htmlTag = htmlTagArray.join("");
                //console.log(htmlTag);
                document.getElementById('tableContent').innerHTML = htmlTag;

                //Clear fields
                clearData();
                
            }).catch(function (error) {
        console.log(error);
        document.getElementById("message").innerHTML = `<p style="color: #ff0000;">Error: ${error}</p>`;
    });
};


function findAllCars() {
    fetch('api/cars/all')
            .then(res => res.json())
            .then(data => {
                //console.log("data", data);
                const htmlTagArray = data.map(currentElement => {
                    //console.log(currentElement);
                    const row = `<tr><td>${currentElement.id}</td><td>${currentElement.brand}</td><td>${currentElement.model}</td><td>${currentElement.year}</td><td>${currentElement.price}</td></tr>`;
                    return row;
                });
                htmlTag = htmlTagArray.join("");
                //console.log(htmlTag);
                document.getElementById('tableContent').innerHTML = htmlTag;

                //Clear fields
                clearData();

            }).catch(function (error) {
        console.log(error);
        document.getElementById("message").innerHTML = `<p style="color: #ff0000;">Error: ${error}</p>`;
    });
}
;

const clearData = () => {
    document.getElementById("car_id").value = '';
    document.getElementById("message").innerHTML = '';
    document.getElementById("car_price").value = '';
    document.getElementById("car_brand").value = '';
};


