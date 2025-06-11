/**
 * 
 */
console.log("boardDetailComment.js in");
console.log(bnoValue);

const cmtText = document.getElementById('cmtText'); // span이면 innerText로 가져오기
const cmtWriter = document.getElementById('cmtWriter');
const cmtUpdateBtn = document.getElementById('cmtUpdateBtn');

document.getElementById('cmtClearBtn').onclick = clearInputs;

document.getElementById('cmtAddBtn').addEventListener('click',  () => {
  if (cmtText.value == '') {
    alert('댓글을 작성해주세요.');
    cmtText.focus();
    return;
  }

  let cmtData = {
    bno : bnoValue,
    writer : cmtWriter.value,
    content: cmtText.value
  };

  console.log(cmtData);
  postCommentToServer(cmtData).then(result => {
    if (result == '1') {
      // 댓글 출력
      spreadCommentList(cmtData.bno);
      clearInputs();
    } else {
      alert("댓글 등록 실패")
    }
  });
});

function clearInputs() {
  cmtText.value = '';
  cmtWriter.value = '';
}

function spreadCommentList(bno, page = 1) {
  getCommentListFromServer(bno, page).then(ph => {
    console.log(ph);
    // result 출력
    /*
    	<div class="row w-75 border-bottom p-2">
        <div class="col-2 border-end">writer</div>
        <div class="col-8 border-end">content</div>
        <div class="col-1">regDate</div>
      </div>
    */
    const {pgvo, cmtList} = ph;

    const comments = document.getElementById('cmtListArea');
    if (page == 1 && cmtList.length == 0) {
      // 댓글이 없을 경우
      comments.innerHTML = `<div class="row p-2 mt-2 justify-content-center">댓글이 없습니다.</div>`;
      return;
    }

    if (page == 1) comments.innerHTML = '';
    for (const cvo of cmtList) {
      let comment = '<div class="container border border-secondary rounded mt-3">';
        comment += '<div class="row w-100 justify-content-evenly">';
          comment += `<div class="col-7 p-0 border-end d-flex justify-content-center align-items-center">${cvo.writer}</div>`;
          comment += `<div class="col-4 px-0 py-2 d-flex justify-content-center align-items-center">${cvo.regDate}</div>`;
          comment += `<div class="col-1 d-flex p-0 justify-content-center align-items-center" data-cno=${cvo.cno}>`;
          if(cvo.writer == nickName) {
            comment += `<button type="button" class="btn btn-outline-warning mod" data-bs-target="#myModal" data-bs-toggle="modal">%</button>`; // 수정 버튼
            comment += `<button type="button" class="btn btn-outline-danger del">X</button>`; // 삭제 버튼
          }
          comment += '</div>';
        comment += '</div>';
        comment += '<div class="row border-top w-100 ms-1">';
        comment += `<div class="col d-flex p-2 align-items-center p-2 ps-3">${cvo.content}</div>`;
      comment += '</div>';
      comments.innerHTML += comment;
    }
    // 더보기 버튼의 숨김 여부 체크
    let moreBtn = document.getElementById('moreBtn');
    // 더보기 버튼이 표시되는 조건 : ph.realEndPage > ph.pgvo.pageNo
    // 현재 페이지가 전체 페이지보다 작으면 표시
    if (ph.realEndPage > pgvo.pageNo) {
      moreBtn.style.visibility = "visible";
      moreBtn.dataset.page = page + 1;
    } else moreBtn.style.visibility = "hidden";
  });
}

document.addEventListener('click', e => {
  // 내가 선택한 객체를 인지 : e.target
  // moreBtn을 선택했을 때....
  if (e.target.id === 'moreBtn') {
    let page = parseInt(e.target.dataset.page); // 문자로 인지
    spreadCommentList(bnoValue, page);
  }

  // 수정 모달 호출. cno, content => 서버로 전송하여 update
  if (e.target.classList.contains('mod')) {
    const container = e.target.closest('.container');
    const cmtWriter = container.firstChild.firstChild.textContent;
    const cmtText = container.lastChild.textContent;
    const cno = e.target.closest('div').dataset.cno;
    console.log(cmtWriter, ' >> ', cmtText);

    document.getElementById('modalWriter').innerHTML = `${cno}. <b>${cmtWriter}</b>`;
    document.getElementById('modalInput').value = cmtText;

    cmtUpdateBtn.setAttribute("data-cno", cno);
  }

  // update
  if (e.target.id === 'cmtUpdateBtn') {
    const cno = cmtUpdateBtn.dataset.cno;
    const modalInput = document.getElementById('modalInput');
    const cmtText = modalInput.value;

    if (cmtText == '') {
      modalInput.focus();
      return;
    }

    // TODO
    let cmtData = {
      cno: cno,
      content: cmtText
    };

    console.log(cmtData);
    updateComment(cmtData).then(result => {
      if (result == '1') {
        alert('댓글 수정 성공');
        const modal = document.getElementById('myModal');
        bootstrap.Modal.getInstance(modal).hide();
        spreadCommentList(bnoValue);
      } else alert('댓글 수정 실패');
    })
  }

  // 삭제
  if (e.target.classList.contains('del')) {
    let div = e.target.closest('div');
    let cno = div.dataset.cno;

    // 비동기로 cno 보내서 db에서 삭제 => isOk
    removeCommentFromServer(cno).then(result => {
      if (result == '1') {
        console.log("댓글 삭제 성공");
      } else {
        console.log('댓글 삭제 실패');
      }
      // 댓글 출력
      spreadCommentList(bnoValue);
    });
  }
});

// update
async function updateComment(cmtData) {
  try {
    const url = '/comment/update';
    const config = {
      method: 'put', // RESTful
      headers: {
        'Content-Type' : 'application/json; charset=utf-8'
      },
      body: JSON.stringify(cmtData)
    };

    const response = await fetch(url, config);
    const result = await response.text();
    return result;
  } catch (error) {
    console.log(error);
  }
}

// delete
async function removeCommentFromServer(cno) {
  try {
    const url = `/comment/${cno}`;
    const config = {
      method: 'delete'
    };
    const resp = await fetch(url, config);
    const result = await resp.text(); // isOk
    return result;
  } catch (error) {
    console.log(error);
  }
}

// get
async function getCommentListFromServer(bno, page) {
  try {
    // RequestParam : /cmt?bno=${bvo.bno}
    // PathVariable : /comment/${bvo.bno}
    const resp = await fetch(`/comment/${bno}/${page}`);
    const result = await resp.json(); // List<CommentVO>
    return result;
  } catch (error) {
    console.log(error);
  }
}

// post
async function postCommentToServer(cmtData) {
  try {
    const url = "/comment/post";
    const config = {
      method : "post",
      headers: {
        'Content-Type' : 'application/json; charset=utf-8'
      },
      body: JSON.stringify(cmtData)
    };
    const response = await fetch(url, config);
    const result = await response.text();
    return result;
  } catch (error) {
    console.log(error);
  }
}