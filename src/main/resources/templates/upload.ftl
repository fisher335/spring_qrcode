<@override name = "body">
<div class="container-fluid">
    <form class="form-horizontal col-sm-10" role="form" action="" method="post" id="info" enctype="multipart/form-data">
        <div class="form-group">
            <label for="file" class="col-sm-3 align-left control-label">上传文件,暂只接受MP4文件</label>
            <div class="col-sm-4" id="upload">
                <input type="file" class="form-control" id="file" name='file'>
            </div>
        </div>

        <div class="form-group">
            <div class="col-sm-offset-3 col-sm-3">
                <input type="button" class="btn btn-primary " value="上传文件" onclick="check()">
            </div>

        </div>

    </form>



<form  role="form" id="fileUploadForm" action="/test" name="fileUploadForm" method="post" enctype="multipart/form-data">
              <div class="form-group">
                <input id="fileFolder" name="fileFolder" type="file" webkitdirectory><span id="msg" style="color:#F00"></span>
              </div>
              <button type="button"  id="subButton" onclick="commit()">Submit</button>
            </form>
</div>

<script language="JavaScript" type="text/javascript">

    function check() {
        var file = document.getElementById('file');
        if (file.value == "") {
            alert("请选择文件");

        }
        else {
            document.getElementById("info").submit()
        }

    }



    //页面提示信息
var msg;
//文件数量限制
var filesCount=2000;
//文件夹大小限制 2000M
var filesSize=2147483648;
//实际的文件数量
var actual_filesCount=0;
//实际的文件夹大小
var actual_filesSize=0;

function commit(){
    //判断是否选中文件夹
    var file=$("#fileFolder").val();
    if(file==''){
        $("#msg").text('请选择要上传的文件夹');
        return;
    }

    $("#fileUploadForm").submit();
//    ajaxfileupload.js 插件提交
//    $.ajaxFileUpload({
//         url:"../upload/uploadFolder",//需要链接到服务器地址
//          secureuri:false,
//          fileElementId:"fileFolder",//文件选择框的id属性  ,//文件选择框的id属性
//          dataType: 'json',   //json
//          contentType: false,    //不可缺
//          processData: false,    //不可缺
//          success: function (data){
//            if(data!=null){
//              alert(data);
//            }else{
//                alert("上传失败");
//            }
//        },
//        error:function(XMLHttpRequest, textStatus, errorThrown){
//        	console.log(XMLHttpRequest);
//        	console.log(textStatus);
//        	console.log(errorThrown);
//            alert("上传失败，请检查网络后重试");
//       }
//    });

}

document.getElementById('fileFolder').onchange = function(e) {
    //判断是否选中文件
      var file=$("#fileFolder").val();
      if(file!=''){
          $("#msg").text('');
      }
      var files = e.target.files; // FileList
      //文件数量
      actual_filesCount = files.length;
      if(actual_filesCount > filesCount){
         $("#msg").text("文件过多，单次最多可上传"+filesCount+"个文件");
         return;
      }
      for (var i = 0, f; f = files[i]; ++i){
          actual_filesSize += f.size;
          if(actual_filesSize > filesSize){
             $("#msg").text("单次文件夹上传不能超过"+filesSize/1024/1024+"M");
             return;
          }
      }
    };





</script>
</@override>
<@extends name="base.ftl"/>