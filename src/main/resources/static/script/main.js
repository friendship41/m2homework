var sidebar = document.getElementById('sidebar');

function sidebarToggle() {
    if (sidebar.style.display === "none") {
        sidebar.style.display = "block";
    } else {
        sidebar.style.display = "none";
    }
}

var profileDropdown = document.getElementById('ProfileDropDown');

function profileToggle() {
    if (profileDropdown.style.display === "none") {
        profileDropdown.style.display = "block";
    } else {
        profileDropdown.style.display = "none";
    }
}



/**
 * ### Modals ###
 */

function toggleModal(action, elem_trigger)
{
    elem_trigger.addEventListener('click', function () {
        if (action == 'add') {
            let modal_id = this.dataset.modal;
            document.getElementById(`${modal_id}`).classList.add('modal-is-open');
        } else {
            // Automaticlly get the opned modal ID
            let modal_id = elem_trigger.closest('.modal-wrapper').getAttribute('id');
            document.getElementById(`${modal_id}`).classList.remove('modal-is-open');
        }
    });
}


// Check if there is modals on the page
if (document.querySelector('.modal-wrapper'))
{
    // Open the modal
    document.querySelectorAll('.modal-trigger').forEach(btn => {
        toggleModal('add', btn);
    });
    
    // close the modal
    document.querySelectorAll('.close-modal').forEach(btn => {
        toggleModal('remove', btn);
    });
}


/**
 * window load시 sidebar 설정
 */
window.addEventListener('load', function () {
    let pathname = window.location.pathname;
    clearAllSidebarSelectedEffect();
    if (pathname === '/dashboard') {
        document.getElementById("sb_li_dashboard").classList.add('bg-white');
    }
    else if (pathname === '/character/list') {
        document.getElementById("sb_li_character_list").classList.add('bg-white');
    } else if (pathname === '/homework/list') {
        document.getElementById("sb_li_homework_list").classList.add('bg-white');
    }
});

function clearAllSidebarSelectedEffect() {
    document.getElementById("sb_li_dashboard").classList.remove('bg-white');
    document.getElementById("sb_li_character_list").classList.remove('bg-white');
    document.getElementById("sb_li_homework_list").classList.remove('bg-white');
}


/**
 * Util function
 */
function isEmpty(value){
    return value === "" || value == null || (typeof value == "object" && !Object.keys(value).length);
}

function commonAjaxFailConsoleLog(jqXHR,status,error) {
    console.log(jqXHR);
    console.log(status);
    console.log(error);
    alert("에러발생! 8ㅅ8....");
    window.location.reload();
}

function closeModal(modalId) {
    document.getElementById(modalId).classList.remove('modal-is-open');
}

function createParamStr(paramList) {
    if (paramList === null || paramList.length === 0) {
        return "";
    }
    let paramStr = "?";
    for (let param of paramList) {
        paramStr += param;
        paramStr += "&"
    }
    return paramStr.substr(0, paramStr.length - 1);
}

// Navigation 숫자버튼만드는 공통 함수
function createPageNavi(pageable, naviObj, prevBtnObj, pageBtnId) {
    totalPageNum = pageable.totalPages;
    currentHomeworkPage = pageable.number;

    naviObj.children().remove('.rounded-l-none');

    let page;
    for (page = pageable.totalPages; page > 0; page--) {
        prevBtnObj.after(createPageBtnHTML(page, pageable.number, pageBtnId))
    }
}

function createPageBtnHTML(page, currentNumber, pageBtnId) {
    let html = '<button class="';
    if (page === currentNumber+1) {
        html += 'bg-teal-400';
    } else {
        html += 'bg-teal-200';
    }
    html += ' hover:bg-teal-500 text-teal-900 font-bold py-2 px-4 rounded-l-none" id="';
    html += pageBtnId;
    html += page;
    html += '" onclick="get' + pageBtnId + 'ByPageNum(';
    html += page;
    html += ')">';
    html += page;
    html += '</button>';
    return html;
}
