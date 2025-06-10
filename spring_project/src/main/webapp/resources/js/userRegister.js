/**
 * 
 */

const [email, nickName, pwd, pwdCheck, emailArea, feedback] = [
  "email", "nickName", "pwd", "pwdCheck", "emailCheckStmt", "feedback"
].map(e => document.getElementById(e));
const emailChecked = false;

// 이메일 중복 체크(비동기)
document.getElementById('emailCheckBtn').addEventListener('click', () => {
  emailChecked = false;
  const emailValue = email.value;
  if (emailValue == '') return;

  fetch(`/user/check?email=${emailValue}`)
  .then(response => response.text())
  .then(result => {
    if (result == 'true') {
      emailChecked = true;
      emailArea.innerText = '사용가능한 이메일입니다.';
      emailArea.classList.remove('text-bg-warning');
      emailArea.classList.add('text-bg-success');
    } else {
      emailChecked = false;
      emailArea.innerText = '이미 사용중인 이메일입니다.';
      emailArea.classList.remove('text-bg-success');
      emailArea.classList.add('text-bg-warning');
    }
  }).catch(console.log);
});