/**
 * 
 */

const email = document.getElementById('email').value;
const [nickName, pwdChangeBtn, pwd, pwdCheck, pwdCheckArea, updateBtn, leaveBtn] = ["nickName", "pwdChangeBtn", "pwd", "pwdCheck", "pwdCheckArea",  "updateBtn", "leaveBtn"].map(e => document.getElementById(e));

let changePwd = false;
let newPwdResult = undefined;
pwdChangeBtn.addEventListener('click', () => {
  changePwd = !changePwd;

  if (changePwd) {
    pwd.disabled = false;
    pwdCheck.disabled = false;
    passCheck();
  } else {
    pwd.disabled = true;
    pwdCheck.disabled = true;
    pwdCheckArea.innerText = '';
    newPwdResult = undefined;
  }
});

pwd.onchange = passCheck;
pwdCheck.onchange = passCheck;

function passCheck() {
  const [pwdValue, pwdCheckValue] = [pwd, pwdCheck].map(e => e.value);

  if (pwdValue == '' && pwdCheckValue == '') {
    pwdCheckArea.innerText = 'Enter new password.';
    pwdCheckArea.style.color = 'black';
    return;
  }
  
  if (pwdValue.length < 8 || !/[a-z]/.test(pwdValue) || !/[A-Z]/.test(pwdValue) || !/[0-9]/.test(pwdValue)) {
    newPwdResult = false;
    pwdCheckArea.innerText = 'Invalid Password';
  } else if (pwdValue != pwdCheckValue) {
    newPwdResult = false;
    pwdCheckArea.innerText = 'Password Match Failed';
  } else {
    newPwdResult = true;
    pwdCheckArea.innerText = 'Valid Password';
  }

  pwdCheckArea.style.color = newPwdResult ? 'green' : 'red';
}

updateBtn.addEventListener('click', () => {
  if (nickName.value == '') nickName.focus();
  else if (changePwd && (newPwdResult != true)) pwd.focus();
  else document.getElementById('updateForm').submit();
});

leaveBtn.onclick = () => {
  if (confirm('정말로 탈퇴하시겠습니까?')) location.href = "/user/remove";
};