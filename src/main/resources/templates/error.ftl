<@override name = "body">
<div class="container-fluid">
<table class="table table-bordered" align="center" sortable="true">
    <caption>出错信息</caption>

    <thead>
    <tr>
        <th>No.</th>
        <th>code</th>
        <th>message</th>
        <th>status</th>
    </tr>
    </thead>
    <tbody>
   <tr>
       <td>1</td>
       <td>${code}</td>
       <td>${msg}</td>
       <td>error</td>
   </tr>


    </tbody>
</table>
</div>
</@override>
<@extends name="base.ftl"/>
