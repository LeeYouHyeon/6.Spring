/**
 * 
 */
console.log("boardModify.js in");

const csrfHeader = document.querySelector('meta[name="_csrf.header"]').getAttribute('X-CSRF-TOKEN');
const csrfToken = document.querySelector('meta[name="_csrf"]').getAttribute('content');

document.addEventListener('click', e => {
  if (e.target.classList.contains('removeFileBtn')
    && confirm("정말로 삭제하시겠습니까?")
  ) {
    const li = e.target.closest('li');
    const uuid = li.dataset.uuid;
    fetch(`/board/deleteFile/${uuid}`, { method: 'delete', [csrfHeader]: csrfToken })
      .then(resp => resp.text())
      .then(result => {
        alert(result == '1' ? "정상적으로 삭제되었습니다." : "삭제에 실패했습니다.");
        if (result == '1') {
          li.remove();
        }
      }).catch(error => {
        console.log(error);
        alert('오류가 발생했습니다.');
      });
  }
});