const imageForm = document.querySelector('form');
const playerList = document.getElementById('playerSelectInput');
const alertContainer = document.getElementById('alertContainer');
const playerImageFileInput = document.getElementById('playerImageFileInput');
const checkFileExistsURL = new URL('http://localhost:3000/image/player/check');
const uploadImageURL = new URL('http://localhost:3000/imageup/player/upload');

imageForm.addEventListener('submit', event => {
   event.preventDefault();
   if (playerList.value == null) {
      alertContainer.innerText = 'Choose player for uploading';
   }
   else if (playerImageFileInput.files.length === 0) {
      alertContainer.innerText = 'Choose file for uploading';
   }
   else {
      let pngRegEx = /\.png$/i;
      if (!pngRegEx.exec(playerImageFileInput.files[0].name)) {
         alertContainer.innerText = "Choose .png file for uploading!";
      }
      else {
         checkFileExistsURL.searchParams.set('fileName', playerImageFileInput.files[0].name);
         fetch (checkFileExistsURL, {
            method: 'GET'
         }).then(function (response) {
            response.text().then(function (text) {
               if (text === 'Exists') {
                  alertContainer.innerText = 'Image with such name exists on server, choose another one';
               }
               else if (text === 'Not exist') {
                  let formData = new FormData();
                  formData.append('playerID', playerList.value);
                  formData.append('playerImage', playerImageFileInput.files[0]);
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