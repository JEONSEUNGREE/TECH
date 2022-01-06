<#import "const.ftl" as const/>
<#import "security.ftl" as info/>

<nav class="navbar navbar-expand-lg navbar-light bg-light">
  <div class="container-fluid">
    <a class="navbar-brand" href="#">${const.headerNav[0]}</a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarColor03" aria-controls="navbarColor03" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarColor03">
      <ul class="navbar-nav me-auto">
        <li class="nav-item">
          <a class="nav-link active" href="#">${const.headerNav[1]}
          </a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="#">${const.headerNav[2]}
          </a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="#">${const.headerNav[3]}
          </a>
        </li>
        </ul>
        <ul class="navbar-nav ml-auto">
        <li class="nav-item">
          <a class="nav-link" href="#">사용자 정보 : ${info.name}
          </a>
        </li>
        </ul> 
    </div>
  </div>
</nav>