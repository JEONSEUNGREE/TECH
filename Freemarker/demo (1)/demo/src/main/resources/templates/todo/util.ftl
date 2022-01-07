<#macro head>
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
  </head>
</#macro>

<#macro body>
    <body>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
    <#assign title="프리마커"?upper_case />
    <h1>${title}</h1>
        <div style="display: inline;">
            <div style="display: inline;">
                <input type="text" placeholder="write" style="width:200px;">
            </div>
            <div style="display: inline;">
                <input type="checkbox" onclick="init()" class="btn-check" id="btn-check-2" checked autocomplete="off">
        <label class="btn btn-primary" for="btn-check-2">click</label>
    </div>
    </div>
  </body>
</#macro>


<#macro html>
<body>
    <div class="main">
        <h1>프리마커로 변환</h1>
        <form>
            <input type="text" placeholder="새로운 할 일" name="todo">
            <button type="submit"><b>추가</b></button>
        </form>
    </div>
</body>
</#macro>

<#macro todo>
    <script>
   init();
function init(){
        document.querySelector('form').addEventListener('submit', addToDo);
}

 
function checkToDo(e){  // 체크박스를 클릭한 경우 글씨 색을 연하게 바꿔준다.
    const todo = e.target.nextSibling;
    if(e.target.checked){
        todo.style.color = "#dddddd";
    }else {
        todo.style.color = "#000000";
    }
}

function clearTodoList(e){ //목록 전체 삭제하는 경우
    let ul = document.querySelector('ul').innerHTML = '';
}

function addToDo(e){ //새로운 할 일 추가하는 경우
    e.preventDefault();
    let toDoValue = document.querySelector('input');
    if(toDoValue.value !== '')
        addTask(toDoValue.value);
        toDoValue.value = ''; //입력창 비워주기
}

function addTask(value){
    let ul = document.querySelector('ul');
    let li = document.createElement('li');
    li.innerHTML = `<span class="delete">x</span><input type="checkbox"><label>${value}</label>`;
    ul.appendChild(li);
    document.querySelector('.todolist').style.display = 'block';
}
    </script>
</#macro>
 