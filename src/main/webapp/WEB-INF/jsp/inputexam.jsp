<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/includes/taglibs.jsp"%>
<div class="container navbar-default">
	<div class='row'>

		<div class="col-lg-8 content-categories-panel">
            <div class="row carousel-holder">
                <div class="col-lg-12">
                    <div class="panel panel-default" id="secondthLink2">
                        <div class="panel-heading">Nhập Câu Hỏi</div>



                        <form action="" method="POST">
                            <div id="parentnew">
                            <div class="form-group text-center">
                                <h4>Nội Dung Câu Hỏi</h4>
                            </div>
                            <div class="row">
                                <div class=" col-lg-3 col-lg-offset-2">
                                    <div class="form-group input-group">

                                        <label for="ExamID">Tên Môn Học : </label>
                                        <select class="form-control" id="subjects" name="subjects" selected="Chọn môn học">
                                            <option>Chọn môn học</option>
                                            <c:forEach items = "${GetSubject}" var="GetSubjects">
                                                <option value="${GetSubjects}"> ${GetSubjects}
                                                </option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </div>
                                <div class=" col-lg-3 col-lg-offset-1">
                                <div class="radio radio-primary">
                                    <input type="radio" name="radio1" id="radio1" value="option1" checked="checked">
                                    <label for="radio1">
                                        Nhập câu hỏi đơn
                                    </label>
                                </div>
                                <div class="radio radio-primary">
                                    <input type="radio" name="radio1" id="radio2" value="option2">
                                    <label for="radio2">
                                        Nhập BLOCK câu hỏi
                                    </label>
                                </div>
                                </div>
                            </div>

                                    <div class="form-group input-group col-lg-3 col-lg-offset-2" id="keyWord">
                                        <label for="keyWord">Nhập Từ Khóa :</label> <input
                                            class="form-control" type="text" name='keyWord'
                                            placeholder="từ khóa cho tìm kiếm" />
                                    </div>



                            <div class="form-group input-group col-lg-8 col-lg-offset-2" id="haveblock" style="display: none">
                                <label for="questionblock">Nhập nội dung dùng chung cho blog :</label> <textarea
                                    class="form-control" type="text" name='questionblock'
                                    placeholder="Nội dung blog"> </textarea>
                            </div>


                        <div id="rcorners2">

                            <div class="form-group input-group col-lg-8 col-lg-offset-2">
                                <label for="question">Nội dung câu hỏi :</label> <input
                                    class="form-control" type="text" name='questioncontent' id="questioncontent"
                                    placeholder="Nhập nội dung" />
                            </div>
                            <div class="row">
                                <div class="col-lg-3 col-lg-offset-2">
                                    <div class="form-group">
                                        <label for="chaptersubjects">Chương :</label>
                                        <select class="form-control" id="chapters" name="chaptername" selected="Chọn chương">
                                            <option>Chọn chương</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="col-lg-3 col-lg-offset-1">
                                    <div class="form-group">
                                        <label for="target">Tiêu chí đánh giá :</label> <input
                                            class="form-control" id="target" type="text" name='targetname'
                                            placeholder="Nhập tiêu chí" />
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class=" col-lg-3 col-lg-offset-2">
                                    <div class="form-group">
                                        <label>Mức Độ Khó :</label>
                                        <select class="form-control" id="level" name="level">
                                            <option>1</option>
                                            <option>2</option>
                                            <option>3</option>
                                            <option>4</option>
                                            <option>5</option>
                                        </select>
                                    </div>
                                </div>
                            </div>

                            <div class="form-group input-group col-lg-10 col-lg-offset-1">
								<textarea class="form-control" rows="10" cols="50" type="text" id="answers"
                                          name='answers' placeholder="Nhập nội dung các đáp án. Vui lòng sử dụng 2 dòng trống để ngăn cách các đáp án. Đối với các kí hiệu toán học hay vật lí không nhập được, vui lòng sử dụng kí hiệu latex "></textarea>
                            </div>


                        </div>


                        </div>
                            <br>

                        <div class="form-group text-center">
                            <a id="addblock" class="btn btn-info" style="display: none">Thêm câu hỏi vào BLOCK</a>
                            <label id="or" style="display: none">HOẶC</label>
                            <button type='submit' class='btn btn-info' name="postquestion"
                                    value='Submit'>Submit</button>
                        </div>


                        <script>

                            $(document).ready(function () {
                                var number = 1;
                                        $('#radio1').click(function() {
                                    if($('#radio1').is(':checked')) {
                                        $('#haveblock').hide();
                                        $('#addblock').hide();
                                        $('#or').hide();
                                        var x = $('#parentnew #rcorners2').first().clone().find("input:text").val("").end();
                                        $("div[id=rcorners2]").remove();
                                        $(x).appendTo("#parentnew");
                                        $('#questioncontent').attr('name', 'questioncontent');
                                        $('#chapters').attr('name', 'chaptername');
                                        $('#target').attr('name', 'targetname');
                                        $('#level').attr('name', 'level');
                                        $('#answers').attr('name', 'answers');
                                        number = 1;


                                    }
                                });
                                $('#radio2').click(function() {
                                    if($('#radio2').is(':checked')) {
                                        $('#haveblock').show();
                                        $('#addblock').show();
                                        $('#or').show();
                                    }
                                });
                                $("#addblock").click(function () {
                                    number = number + 1;
                                    var x = $('#parentnew #rcorners2').first().clone().find("input:text").val("").end().each(function() {
                                        $('#questioncontent').attr('name', "questioncontent" + number);
                                        $('#chapters').attr('name', "chaptername" + number);
                                        $('#target').attr('name', "targetname" + number);
                                        $('#level').attr('name', "level" + number);
                                        $('#answers').attr('name', "answers" + number);

                                    });
                                   $(x).appendTo("#parentnew");
                                });

                                $("#subjects").on('change', function (e) {
                                    var optionSelected = $("option:selected", this);
                                    var valueSelected = this.value;
                                    if (valueSelected != "Chọn môn học") {
                                        event.preventDefault();
                                        var search = {
                                            "pName" : $("#subjects").val()
                                        }

                                        $.ajax({
                                            type : "POST",
                                            contentType : "application/json",
                                            url : "${home}/inputexam2",
                                            data : JSON.stringify(search),
                                            dataType : 'json',
                                            timeout : 100000,
                                            success : function(data) {
                                                $('select[id*=chapters]')
                                                        .find('option')
                                                        .remove()
                                                        .end()
                                                        .append('<option value="Chọn chương">Chọn chương</option>')
                                                        .val('Chọn chương')
                                                ;
                                                for (var i=0; i<data.length;i++){
                                                    $('select[id*=chapters]')
                                                        .append($('<option>', {
                                                            value: data[i],
                                                            text: data[i]
                                                        }));

                                                }

                                            }
                                        });
                                    }
                                });

                            });

                        </script>
                        </form>

                    </div>



                    <div class="panel panel-default" id="thirdthLink5">
                        <form id="question" action="" method="POST">
                            <div class="panel-heading">Dán Latex</div>
                            <div class="row">
                                <div class=" col-lg-3 col-lg-offset-2">
                                    <div class="form-group input-group">

                                        <label for="ExamID">Tên Môn Học : </label> <input
                                            class="form-control" type="text" name='subjectsname'
                                            placeholder="Tên Môn Học" />
                                    </div>
                                </div>


                            </div>

                            <div class="form-group input-group col-lg-10 col-lg-offset-1">
								<textarea class="form-control" rows="10" cols="50" type="text"
                                          name='latex' placeholder="Copy code Latex vào đây"></textarea>
                            </div>


                            <div class="form-group text-center">
                                <button type='submit' class='btn btn-info' name="postlatex"
                                        value='Submit'>Submit</button>
                            </div>
                        </form>
                    </div>


                    <div class="panel panel-default" id="thirdthLink2">
                        <form id="question2" action="" method="POST">
                            <div class="panel-heading">Dán Word</div>

                            <div class="row">
                                <div class=" col-lg-3 col-lg-offset-2">
                                    <div class="form-group input-group">

                                        <label for="ExamID">Tên Môn Học : </label> <input
                                            class="form-control" type="text" name='subjectsname'
                                            placeholder="Tên Môn Học" />
                                    </div>
                                </div>

                                <div class=" col-lg-3 col-lg-offset-1">
                                    <div class="form-group">
                                        <label>Mức Độ Khó :</label> <select class="form-control"
                                                                            name="levelq">
                                        <option>1</option>
                                        <option>2</option>
                                        <option>3</option>
                                        <option>4</option>
                                        <option>5</option>
                                    </select>
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class=" col-lg-3 col-lg-offset-2">
                                    <div class="form-group">
                                        <label for="chaptersubjects">Chương :</label> <input
                                            class="form-control" type="text" name='chaptername'
                                            placeholder="ví dụ : 1 or Mở đầu" />
                                    </div>
                                </div>
                            </div>

                            <hr />
                            <div class="row">
                                <div class=" col-lg-8 col-lg-offset-2">
                                    <div class="form-group">
                                        <label>Nhập dấu hiệu nhận biết trước câu hỏi và các
                                            đáp án : </label>
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class=" col-lg-4 col-lg-offset-2">
                                    <div class="form-group input-group">
                                        <input class="form-control" type="text" name='stringexam'
                                               placeholder="Ví dụ : Câu 1." />
                                    </div>
                                </div>
                                <div class=" col-lg-4">
                                    <div class="form-group input-group">
                                        <input class="form-control" type="text"
                                               name='stringsolutionA' placeholder="Ví dụ : A." />
                                    </div>
                                </div>
                            </div>

                            <div class="row">
                                <div class=" col-lg-4 col-lg-offset-2">
                                    <div class="form-group input-group">
                                        <input class="form-control" type="text" name='stringsolutionB'
                                               placeholder="Ví dụ : B." />
                                    </div>
                                </div>
                                <div class=" col-lg-4">
                                    <div class="form-group input-group">
                                        <input class="form-control" type="text" name='stringsolutionC'
                                               placeholder="Ví dụ : C." />
                                    </div>
                                </div>
                            </div>

                            <div class="row">
                                <div class=" col-lg-4 col-lg-offset-2">
                                    <div class="form-group input-group">
                                        <input class="form-control" type="text" name='stringsolutionD'
                                               placeholder="Ví dụ : D." />
                                    </div>
                                </div>
                                <div class=" col-lg-4">
                                    <div class="form-group input-group">
                                        <input class="form-control" type="text" name='stringsolutionE'
                                               placeholder="Ví dụ : E." />
                                    </div>
                                </div>
                            </div>

                            <div class="form-group input-group col-lg-10 col-lg-offset-1">
								<textarea class="form-control" rows="10" cols="50" type="text"
                                          name='word' placeholder="Copy Word vào đây"></textarea>
                            </div>


                            <div class="form-group text-center">
                                <button type='submit' class='btn btn-info' name="postword"
                                        value='Submit'>Submit</button>
                            </div>
                        </form>
                    </div>



                    <div class="panel panel-default" id="fivethLink2">
                        <div class="panel-heading">Import File</div>
                        <form method="POST" enctype=“multipart/form-data”>
                            <div class="form-group input-group">
								<span class="input-group-addon"><i
                                        class="glyphicon glyphicon-folder-open"></i></span> <input
                                    class="form-control" type="file" name='importfile'
                                    placeholder="Import File" />
                            </div>
                            <div class="form-group text-center">
                                <button type='submit' class='btn btn-info' name="importfile"
                                        value='Submit'>Submit</button>
                            </div>

                        </form>
                    </div>

                </div>
            </div>
		</div>

		<div class="col-lg-4">
            <div class="panel panel-default" id="filter">
                <div class="panel-heading">Filter</div>

                    <div class="col-lg-4 col-lg-offset-1">
                        <div class="form-group">
                            <label for="Filter">Filter :</label>
                        </div>
                    </div>
                    <div class="form-group input-group col-lg-4 col-lg-offset-2">
                        <input
                            class="form-control" type="text" name='questionblock'
                            placeholder="Search" />
                    </div>


            </div>
		</div>

	</div>
</div>

