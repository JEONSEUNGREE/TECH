<#import "/freemarker/util.ftl" as util/>

<#macro click >
 <script>
     function add() {
         let count = 'count';
         let tmp = localStorage.getItem(count);
            if(tmp != null) {
                let num = parseInt(tmp)
                localStorage.setItem(count, num + 1);
                if(num > 50) {
                    location.replace("http://localhost:7777/click2")
                }
            }else{
                localStorage.setItem(count, 1);
            }
    
     }
 </script>  
</#macro>


