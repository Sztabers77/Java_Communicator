'use strict';

var usernamePage = document.querySelector('#username-page');
var chatPage = document.querySelector('#chat-page');
var usernameForm = document.querySelector('#usernameForm');
var messageForm = document.querySelector('#messageForm');
var messageInput = document.querySelector('#message');
var messageArea = document.querySelector('#messageArea');
var connectingElement = document.querySelector('.connecting');

var stompClient = null;
var username = null;

var colors = [
  '#2196F3',
  '#32c787',
  '#00BCD4',
  '#ff5652',
  '#ffc107',
  '#ff85af',
  '#FF9800',
  '#39bbb0',
];

function connect(event) {
  username = document.querySelector('#name').value.trim();

  if (username) {
    usernamePage.classList.add('hidden');
    chatPage.classList.remove('hidden');

    var socket = new SockJS('/ws');
    stompClient = Stomp.over(socket);

    stompClient.connect({}, onConnected, onError);
  }
  event.preventDefault();
}

function onConnected() {
  // Subscribe to the Public Topic
  stompClient.subscribe('/topic/public', onMessageReceived);

  // Tell your username to the server
  stompClient.send(
    '/app/chat.register',
    {},
    JSON.stringify({ sender: username, type: 'JOIN' })
  );

  connectingElement.classList.add('hidden');
}

function onError(error) {
  connectingElement.textContent =
    'Could not connect to WebSocket server. Please refresh this page to try again!';
  connectingElement.style.color = 'red';
}

function send(event) {
  var messageContent = messageInput.value.trim();

  if (messageContent && stompClient) {
    var chatMessage = {
      sender: username,
      content: messageInput.value,
      type: 'CHAT',
    };

    stompClient.send('/app/chat.send', {}, JSON.stringify(chatMessage));
    messageInput.value = '';
  }
  event.preventDefault();
}

function onMessageReceived(payload) {
  var message = JSON.parse(payload.body);

  var messageElement = document.createElement('li');

  if (message.type === 'JOIN') {
    messageElement.classList.add('event-message');
    message.content = message.sender + ' joined!';
  } else if (message.type === 'LEAVE') {
    messageElement.classList.add('event-message');
    message.content = message.sender + ' left!';
  } else {
    messageElement.classList.add('chat-message');

    var avatarElement = document.createElement('i');
    var avatarText = document.createTextNode(message.sender[0]);
    avatarElement.appendChild(avatarText);
    avatarElement.style['background-color'] = getAvatarColor(message.sender);

    messageElement.appendChild(avatarElement);

    var usernameElement = document.createElement('span');
    var usernameText = document.createTextNode(message.sender);
    usernameElement.appendChild(usernameText);
    messageElement.appendChild(usernameElement);
  }

  var textElement = document.createElement('p');
  var messageText = document.createTextNode(message.content);
  textElement.appendChild(messageText);

  messageElement.appendChild(textElement);

  messageArea.appendChild(messageElement);
  messageArea.scrollTop = messageArea.scrollHeight;
}

function getAvatarColor(messageSender) {
  var hash = 0;
  for (var i = 0; i < messageSender.length; i++) {
    hash = 31 * hash + messageSender.charCodeAt(i);
  }

  var index = Math.abs(hash % colors.length);
  return colors[index];
}

async function uploadFile() {
  var fileInput = document.getElementById('fileUpload');

  if (fileInput.files.length > 0) {
    let formData = new FormData();
    formData.append('file', fileUpload.files[0]);

    let res = await fetch('/upload', {
      method: 'POST',
      body: formData,
    });

    res.status == 200 &&
      alert('File uploaded to {project-root}/tmp directory!');
  } else {
    alert('Please select file');
  }
}

async function uploadFileFromDb() {
  var fileInput = document.getElementById('fileUploadToDb');

  if (fileInput.files.length > 0) {
    let formData = new FormData();
    formData.append('file', fileUploadToDb.files[0]);

    let res = await fetch('/db.upload', {
      method: 'POST',
      body: formData,
    });

    res.status == 200 &&
    alert('File uploaded to db!');
  } else {
    alert('Please select file');
  }
}

async function getFilesFromDb() {
  const res = await fetch('/files')
  const data = await res.json();
  console.log(data)

  let html = "<table>";
  for (let i = 0; i < data.length; i++) {
    html += "<tr>";
    html += "<td>" + data[i].name + "</td>";
    html += "<td>" + data[i].size + "</td>";
    html += "<td>" + data[i].type + "</td>";
    html += "<td>" + data[i].url + "</td>";

    html += "</tr>";

  }
  html += "</table>";
  document.getElementById("download-box").innerHTML = html;

}
// tabela HTML


function fileValidation() {
  const fileInput = document.getElementById('fileUpload');
  //czy jest pusty
  if (fileInput.files.length > 0) {
    const filePath = fileInput.value;
    //czy jest xml
    const allowedExtension = /(\.xml)$/i;
    if (!allowedExtension.exec(filePath)) {
      alert('Invalid file type');
      fileInput.value = '';
      return false;
    }
  } else {
    alert('Please select file');
  }
}

usernameForm.addEventListener('submit', connect, true);
messageForm.addEventListener('submit', send, true);
