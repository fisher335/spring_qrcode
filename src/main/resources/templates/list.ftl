<@override name = "body">
    <table class="table table-bordered table-striped table-responsive" align="center" sortable = "true" >
	<caption>信息查询</caption>

   <thead>
      <tr>
         <th>键</th>
          <th>值</th>
      </tr>
   </thead>
   <tbody>
     <#list headers?keys as key>
<tr>

<td>${key}</td>
<td>${headers["${key}"]}</td>
   </tr>
</#list>
   </tbody>

</table>
</@override>
<@extends name="base.ftl"/>