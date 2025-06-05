/**
 * 
 */

console.log('boardRegisterFile.js in');
const fileInputBtn = document.getElementById('file');
const fileZone = document.getElementById('fileZone');
const regBtn = document.getElementById('regBtn');

// file button trigger
document.getElementById('trigger').addEventListener('click', () => {
  fileInputBtn.click();
});

// 파일에 대한 정규식 작성
// 1. 실행파일 금지
// 2. 20MB 이상은 금지
const regExp = new RegExp("\.(exe|jar|msi|dll|sh|bat)$");
const maxSize = 1024*1024*20;

function fileValidation(fileName, fileSize) {
  if (regExp.test(fileName)) return 0;
  if (fileSize > maxSize) return 0;
  return 1;
}

fileInputBtn.addEventListener('change', () => {
  let isOk = 1; // 검증을 통과했는지의 여부

  let ul = '<ul class="list-group list-group-flush">';
  for (const file of fileInputBtn.files) {
    const validResult = fileValidation(file.name, file.size);
    isOk *= validResult;
    let li = '<li class="list-group-item">';
    li += '<div class="mb-3">';
    li += `<div class="fw-bold text-${validResult ? 'success' : 'danger'}">업로드 ${validResult ? '' : '불'}가능</div>`;
    li += `${file.name}</div>`;
    li += `<span class="badge text-bg-${validResult ? 'success' : 'danger' }">${file.size} Bytes</span>`;
    li += '</li>';
    ul += li;
  }
  ul += '</ul>';
  fileZone.innerHTML = ul;

  regBtn.disabled = isOk == 0;
});