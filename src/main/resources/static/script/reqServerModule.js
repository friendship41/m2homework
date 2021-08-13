// 캐릭터 관련 request -> /character
function getCharacterJobList(doneCallbackFunc) {
    $.ajax({
        url: "/rest/character/job/list",
        method: "GET",
        dataType: "json"
    }).done(doneCallbackFunc).fail(function (jqXHR,status,error) {
        commonAjaxFailConsoleLog(jqXHR,status,error)
    });
}

function getCharacterList(isMain, page, size, sort, doneCallbackFunc) {
    let url = "/rest/character/list";
    let paramList = [];
    if (isMain !== null) {
        paramList.push("isMain=" + isMain);
    }
    if (page !== null) {
        paramList.push("page=" + page);
    }
    if (size !== null) {
        paramList.push("size=" + size);
    }
    if (sort !== null) {
        paramList.push("sort=" + sort);
    }
    url += createParamStr(paramList);
    $.ajax({
        url: url,
        method: "GET",
        dataType: "json"
    }).done(doneCallbackFunc).fail(function (jqXHR,status,error) {
        commonAjaxFailConsoleLog(jqXHR,status,error);
    })
}

function postCharacter(characterName, jobType, isMain, doneCallback) {
    let reqPostCharacter = {};
    reqPostCharacter.characterName = characterName;
    reqPostCharacter.jobType = jobType;
    reqPostCharacter.isMain = isMain;
    $.ajax({
        url: "/rest/character",
        method: "POST",
        dataType: "json",
        contentType: "application/json;charset=utf-8",
        data: JSON.stringify(reqPostCharacter)
    }).done(doneCallback).fail(function (jqXHR,status,error) {
        commonAjaxFailConsoleLog(jqXHR,status,error);
    })
}

function postCharacterImage() {
    let formData = new FormData($('#characterAddForm')[0]);

    $.ajax({
        url: '/rest/character/image',
        type: "POST",
        enctype: 'multipart/form-data',
        data: formData,
        processData: false,
        contentType: false,
        cache: false
    }).done(function () {
        refreshMainCharacterList();
    }).fail(function (jqXHR,status,error) {
        commonAjaxFailConsoleLog(jqXHR,status,error);
        alert("이미지 업로드 실패!");
        refreshMainCharacterList();
    })
}



// 숙제 관련 request -> /homework
function getHomeworkTargetTypeList(doneCallback) {
    $.ajax({
        url: "/rest/homework/targetType/list",
        method: "GET",
        dataType: "json"
    }).done(doneCallback).fail(function (jqXHR,status,error) {
        commonAjaxFailConsoleLog(jqXHR,status,error);
    })
}

function getMemberHomeworkList(page, size, doneCallback) {
    let url = "/rest/homework";
    let paramList = [];
    if (page !== null) {
        paramList.push("page=" + page);
    }
    if (size !== null) {
        paramList.push("size=" + size);
    }
    url += createParamStr(paramList);
    $.ajax({
        url: url,
        method: "GET",
        dataType: "json"
    }).done(doneCallback).fail(function (jqXHR,status,error) {
        commonAjaxFailConsoleLog(jqXHR,status,error);
    })
}

function getCharacterHomeworkList(characterNo, page, size, doneCallback) {
    let url = "/rest/homework/character";
    let paramList = [];
    if (characterNo !== null) {
        paramList.push("characterNo=" + characterNo);
    }
    if (page !== null) {
        paramList.push("page=" + page);
    }
    if (size !== null) {
        paramList.push("size=" + size);
    }
    url += createParamStr(paramList);
    $.ajax({
        url: url,
        method: "GET",
        dataType: "json"
    }).done(doneCallback).fail(function (jqXHR,status,error) {
        commonAjaxFailConsoleLog(jqXHR,status,error);
    })
}

function postHomework(
    homeworkName, homeworkType, homeworkTargetType, unitGoal, unitResetPeriod, maxGoal, allResetPeriod, doneCallback) {
    let reqPostHomework = {};
    reqPostHomework.homeworkName = homeworkName;
    reqPostHomework.homeworkType = homeworkType;
    reqPostHomework.homeworkTargetType = homeworkTargetType;
    reqPostHomework.unitGoal = unitGoal;
    reqPostHomework.unitResetPeriod = unitResetPeriod;
    reqPostHomework.maxGoal = maxGoal;
    reqPostHomework.allResetPeriod = allResetPeriod;
    $.ajax({
        url: "/rest/homework",
        method: "POST",
        dataType: "json",
        contentType: "application/json;charset=utf-8",
        data: JSON.stringify(reqPostHomework)
    }).done(doneCallback).fail(function (jqXHR,status,error) {
        commonAjaxFailConsoleLog(jqXHR,status,error);
    })
}

function postCharacterHomework(characterNo, homeworkNo, doneCallback) {
    let reqPostCharacterHomework = {};
    reqPostCharacterHomework.characterNo = characterNo;
    reqPostCharacterHomework.homeworkNo = homeworkNo;
    $.ajax({
        url: "/rest/homework/character",
        method: "POST",
        dataType: "json",
        contentType: "application/json;charset=utf-8",
        data: JSON.stringify(reqPostCharacterHomework)
    }).done(doneCallback).fail(function (jqXHR,status,error) {
        commonAjaxFailConsoleLog(jqXHR,status,error);
    })
}

function deleteCharacterHomework(characterNo, homeworkNo, doneCallback) {
    let reqDeleteCharacterHomework = {};
    reqDeleteCharacterHomework.characterNo = characterNo;
    reqDeleteCharacterHomework.homeworkNo = homeworkNo;
    $.ajax({
        url: "/rest/homework/character",
        method: "DELETE",
        dataType: "json",
        contentType: "application/json;charset=utf-8",
        data: JSON.stringify(reqDeleteCharacterHomework)
    }).done(doneCallback).fail(function (jqXHR,status,error) {
        commonAjaxFailConsoleLog(jqXHR,status,error);
    })
}
