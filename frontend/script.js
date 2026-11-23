let properties = [
    {
        id: 1,
        title: "Luxury Villa",
        type: "Villa",
        location: "Trichy",
        price: 6000,
        status: "Available"
    },
    {
        id: 2,
        title: "Modern Apartment",
        type: "Apartment",
        location: "Chennai",
        price: 5000,
        status: "Available"
    }
];

function displayProperties(data) {
    let output = "";
    data.forEach(property => {
        output += `
            <div class="property">
                <h2>${property.title}</h2>
                <p>Location: ${property.location}</p>
                <p>Type: ${property.type}</p>
                <p>Price: ₹${property.price}</p>
                <p>Status: ${property.status}</p>

                <button onclick="selectProperty(${property.id})">
                    Book Property
                </button>
            </div>
        `;
    });

    document.getElementById("properties").innerHTML = output;
}

function searchProperty() {

    let location = document.getElementById("location").value.toLowerCase();

    let result = properties.filter(p =>
        p.location.toLowerCase().includes(location)
    );

    displayProperties(result);
}

let selectedProperty = null;

function selectProperty(id) {

    selectedProperty = properties.find(p => p.id === id);

    if (selectedProperty.status === "Booked") {
        alert("Already Booked!");
        return;
    }

    document.getElementById("bookingForm").scrollIntoView();
}

document.getElementById("bookingForm").addEventListener("submit", function(e) {

    e.preventDefault();

    if (!selectedProperty || selectedProperty.status === "Booked") {
        alert("Please select an available property.");
        return;
    }

    let booking = {
        name: document.getElementById("name").value,
        phone: document.getElementById("phone").value,
        email: document.getElementById("email").value,
        date: document.getElementById("date").value
    };

    selectedProperty.status = "Booked";

    alert("Property Booked Successfully!");

    console.log(booking);

    displayProperties(properties);

    this.reset();
    selectedProperty = null;
});

displayProperties(properties);