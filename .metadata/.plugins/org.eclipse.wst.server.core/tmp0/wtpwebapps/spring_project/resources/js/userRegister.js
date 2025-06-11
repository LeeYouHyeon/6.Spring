/**
 * 
 */

const [email, nickName, pwd, pwdCheck, emailArea, feedback, joinBtn, pwdCheckArea] = [
  "email", "nickName", "pwd", "pwdCheck", "emailCheckStmt", "feedback", "joinBtn", "pwdCheckArea"
].map(e => document.getElementById(e));

// 이메일 중복 체크(비동기)
let emailResult = undefined;
email.onchange = setEmailUndefined;
document.getElementById('emailCheckBtn').addEventListener('click', () => {
  const emailValue = email.value;
  if (emailResult != undefined || emailValue == '') return;

  isRegistered(emailValue).then(result => {
    if (result == '0') setEmailSuccess();
    else setEmailFail();
  }).catch(console.log);
});

async function isRegistered(emailValue) {
  try {
    const response = await fetch(`/user/check/${emailValue}`);
    const result = await response.text();
    return result;
  } catch (error) {
    console.log(error);
  }
}

function setEmailUndefined() {
  emailResult = undefined;
  emailArea.innerText = '이메일 중복 체크를 해주세요.';
  emailArea.style.color = 'black';
}
function setEmailSuccess() {
  emailResult = true;
  emailArea.innerText = '사용 가능한 이메일입니다.';
  emailArea.style.color = 'green';
}
function setEmailFail() {
  emailResult = false;
  emailArea.innerText = '사용중인 이메일입니다.';
  emailArea.style.color = 'red';
}

// 비밀번호 확인
let pwdResult = 0;
pwd.onchange = passCheck;
pwdCheck.onchange = passCheck;
function passCheck() {
  const [pwdInput, pwdCheckInput] = [pwd, pwdCheck].map(e => e.value);

  if (pwdInput == '' && pwdCheckInput == '') {
    pwdResult = 0;
    pwdCheckArea.innerText = 'Password Required';
  } else if (pwdInput.length < 8 || !/[a-z]/.test(pwdInput) || !/[A-Z]/.test(pwdInput) || !/[0-9]/.test(pwdInput)) {
    pwdResult = 1;
    pwdCheckArea.innerText = 'Invalid Password';
  } else if (pwdInput != pwdCheckInput) {
    pwdResult = 1;
    pwdCheckArea.innerText = 'Password Match Failed';
  } else {
    pwdResult = 2;
    pwdCheckArea.innerText = 'Valid';
  }

  pwdCheckArea.style.color = ['black', 'red', 'green'][pwdResult];
}

// 최종 제출
joinBtn.onclick = () => {
  
  if (emailResult != true) email.focus();
  else if (pwdResult < 2) pwd.focus();
  else if (nickName.value == '') nickName.focus();
  else document.getElementById('registerForm').submit();
}