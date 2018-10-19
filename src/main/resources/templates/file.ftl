<@override name = "body">
<div class="container-fluid">
<table class="table table-bordered" align="center" sortable="true">
    <caption>视频文件</caption>

    <thead>
    <tr>
        <th>No.</th>
        <th>文件名称</th>
        <th>播放</th>
    </tr>
    </thead>
    <tbody>
   <#list file as item>

   <tr>
       <td>${item_index+1}</td>
       <td>${item}</td>
       <td><a href="/file/${item}" target="_blank">播放</a></td>
   </tr>


   </#list>
    </tbody>
</table>
</div>
</@override>
<@extends name="base.ftl"/>
