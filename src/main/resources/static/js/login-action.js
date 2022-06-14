const form = document.getElementById('form');
const username = document.getElementById('username');
const email = document.getElementById('email');
const password = document.getElementById('password');
const password2 = document.getElementById('password2');



window.onload=function() {
    form.addEventListener('submit', e => {
        e.preventDefault();
        checkInputs();
    });
}

function checkInputs() {

    const usernameValue = username.value.trim();
    const emailValue = email.value.trim();
    const passwordValue = password.value.trim();
    const password2Value = password2.value.trim();
    var regex = /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{6,20}$/;


    if(usernameValue === '' || usernameValue == null) {
        setErrorFor(username, 'Username cannot be blank');

    }else if(usernameValue.length < 6) {
        setErrorFor(username, 'Username must contain 6 or more characters');

    } else if(usernameValue.length >= 20) {
        setErrorFor(username, 'Username too long');

    } else {
        setSuccessFor(username);
    }


    if(emailValue === '' || emailValue == null) {
        setErrorFor(email, 'Email cannot be blank');

    } else if (!isEmail(emailValue)) {
        setErrorFor(email, 'Not a valid email');
    }  else {
        setSuccessFor(email);
    }

    if(passwordValue === '' || passwordValue == null) {
        setErrorFor(password, 'Password cannot be blank');

    }else if(passwordValue.length < 6) {
        setErrorFor(password, "Pasword must contain 6 or more characters");

    } else if(passwordValue.length >= 20) {
        setErrorFor(password, 'Password too long');

    } else if(!passwordValue.match(regex)) {
        alert('Insert a password between 6 to 20 characters which contain at least one numeric digit, one uppercase and one lowercase letter');
    } else {
        setSuccessFor(password);
    }

    if(password2Value === '' || password2Value == null) {
        setErrorFor(password2, 'Password Two cannot be blank');
    } else if(passwordValue !== password2Value) {
        setErrorFor(password2, 'Passwords does not match');
    } else {
        setSuccessFor(password2);
    }
}



function setErrorFor(input, message) {
    const formControl = input.parentElement;
    const small = formControl.querySelector('small');

    small.innerText = message;

    formControl.className = 'form-control error';
}

function setSuccessFor(input) {
    const formControl = input.parentElement;
    formControl.className = 'form-control success';
}

/*function isEmail(email) {
	return /^(([^<>()\\\.,;:\@"]+(\[^<>()\\\.,;:\@"]+)*)|(".+"))@((\[0-9]{1,3}\[0-9]{1,3}\[0-9]{1,3}\[0-9]{1,3}])|(([a-zA-Z\0-9]+\)+[a-zA-Z]{2,}))$/.test(email);
}*/


function isEmail(email) {
    const re = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    return re.test(String(email).toLowerCase());
}





