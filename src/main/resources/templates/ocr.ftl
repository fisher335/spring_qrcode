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


</div>

<div class="container-fluid" align="center">

        识别结果：<br>
        ${ocr?default("")}

</div>

<script language="JavaScript" type="text/javascript">

    function check() {
        var file = document.getElementById("file");
        if (file.value == "") {
            alert("请选择文件");

        }
        else {
            document.getElementById("info").submit()
        }

    }


</script>
</@override>
<@extends name="base.ftl"/>