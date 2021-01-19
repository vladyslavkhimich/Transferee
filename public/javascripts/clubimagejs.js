const imageForm = document.querySelector('form');
const clubList = document.getElementById('clubSelectInput');
const alertContainer = document.getElementById('alertContainer');
const clubImageFileInput = document.getElementById('clubImageFileInput');
const checkFileExistsURL = new URL('http://localhost:3000/image/club/check');
const uploadImageURL = new URL('http://localhost:3000/imageup/club/upload');

imageForm.addEventListener('submit', event => {
    event.preventDefault();
    if (clubList.value == null) {
        alertContainer.innerText = 'Choose club for uploading';
    }
    else if (clubImageFileInput.files.length === 0) {
        alertContainer.innerText = 'Choose file for uploading';
    }
    else {
        let pngRegEx = /\.png$/i;
        if (!pngRegEx.exec(clubImageFileInput.files[0].name)) {
            alertContainer.innerText = "Choose .png file for uploading!";
        }
        else {
            checkFileExistsURL.searchParams.set('fileName', clubImageFileInput.files[0].name);
            fetch (checkFileExistsURL, {
                method: 'GET'
            }).then(function (response) {
                response.text().then(function (text) {
                    if (text === 'Exists') {
                        alertContainer.innerText = 'Image with such name exists on server, choose another one';
                    }
                    else if (text === 'Not exist') {
                        let formData = new FormData();
                        formData.append('clubID', clubList.value);
                        formData.append('clubImage', clubImageFileInput.files[0]);
                        fetch(uploadImageURL, {
                            method: 'POST',
                            body: formData
                        }).then(function (response) {
                            response.text().then(text => {
                                if (text === 'Updated')
                                    alertContainer.innerText = 'Successfully updated';
                                else if (text === 'Not updated')
                                    alertContainer.innerText = 'Update failed';
                            });
                        })
                    }
                })
            });
        }
    }
});