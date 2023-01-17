function detailSetM(rest){
    rest.append(detailSet());
    var rowM=$("#rowM");
    var rowC
    rowNum=0
    for(i=0;i<restjs.length;i++){
        if(restjs[i].row){
            rowNum+=1
            rowM.append(
            `
            <div class="row" id=`+"row"+rowNum+`>            
            </div>
            ` 
                )
            rowC=$("#row"+rowNum);
        }
        if(restjs[i].break){
            rowM.append("<br>")
            rowC.append(detailSet2(i,12))
        }else{
            rowC.append(detailSet2(i,3))
        }
        
    }
}

function detailSet(){
	return `<div id="all" style="display:none;">
        <div class="card-header bg-white border-0">
            <div class="row align-items-center">
                <div class="col-8">
                    <h3 class="mb-0">`+namejs[1]+`</h3>
                </div>
                <div class="col-4 text-right">
                    <a href="#" onclick="$('#all').hide();$('.mainT').show();" class="btn btn-sm btn-primary">Geri</a>
                </div>
            </div>
        </div>
        <div class="card-body">
            <form>
                <div class="pl-lg-4" id="rowM">
                </div>
            </form>
        </div>
    </div>
	`
}

function detailSet2(i,j){
	return	`<div class="col-lg-`+j+`">
	            <div class="form-group focused" >
	                <label class="form-control-label hmm">`+restjs[i].content+`</label>
	                <div class="dummy"></div>
	            </div>
	        </div>
	        `
}

function setDetailHead(){
        detailSetM($('#rest'))
        setDetailData()
        
    }

function setDetailData(){
    $.get( link+num, function( data ) {
        var listR=$(".dummy");
        var project=data;
        for(i=0;i<restjs.length;i++){
            $(listR[i]).empty()
            $(listR[i]).append("<h3>"+project[restjs[i].type]+"</h3>");
        }
    });
}

function setHead(){
    $("#Namejs").prepend(namejs[0])
    for(i=0;i<tablejs.length;i++){
        $("#headM").append("<th scope='col' width="+tablejs[i].width+">"+tablejs[i].content+"</th>");
    }
}

 function searchFromPageLink(currentPage){
        search(currentPage);
    }

function fillTrContent(number, project){
        project.number=number;
        let trStyle;
        if(isOdd(number)){
            trStyle = "font-weight";
        }
        else{
            trStyle = "bold";
        }
        return content(trStyle,project);
    }
    
function content(trStyle,project){
    var text="<tr class='elements' style="+trStyle+">";
    text+=`
        <td style="display:none;">
        `+project["id"]+`
        </td>`;
    for(i=0;i<tablejs.length;i++){
        if(i!=0 && i<3){
            text+=`<td style="text-overflow: ellipsis; overflow: hidden; white-space: nowrap;" >
                        <a href="javascript:clickItem(
                            ` + project.id + ` 
                        );" style="color:inherit;">
                        ` + project[tablejs[i].type] + `
                        </a>
                    </td>`;
        }else{
            text+=`
            <td style="text-overflow: ellipsis; overflow: hidden; white-space: nowrap;">
            `+project[tablejs[i].type]+`
            </td>`;
        }
    }
    return text
}

function clickItem(ItemId){
    num=ItemId;
    $(".mainT").hide();
    setDetailData();
    $('#all').show();
}

function toExcel(){     
        var searchText = document.getElementById('search_input').value;     
        window.location = link+"excel?text=" + searchText ;
    }

function search(currentPage){

        $("#projects tr").remove();
        $("#pageLinks ul").remove();
        let trContent;
        let pageLinksContent;
        var searchText = document.getElementById('search_input').value;
        $.ajax({
            url: getSearchLink(searchText, currentPage),
            dataType:"json",
            success: function(projectList){
                //toplam kayit sayisi guncelle
                $("#recordCount").text(projectList.totalElements);
                var len=$(projectList.content).length;
                $(projectList.content).each( function(index, project){
                    trContent = fillTrContent(index+1+(len*currentPage||0), project);
                    $("#projects").append(trContent);
                });
                pageLinksContent = getPageLinksContent(projectList.number, projectList.totalPages);
                $("#pageLinks").append(pageLinksContent);
                $(".elements").dblclick(function(){
                    num=parseInt($($(this).children()[0]).text())
                    $(".mainT").hide();
                    setDetailData();
                    $('#all').show();
                });
            }
        });
    }

    function getSearchLink(searchText,currentPage){
        searchText = $("#person").val() || searchText;
        searchText = searchText || "";
        currentPage = currentPage || 0;
        if(projetipi){
            return link+"search?projetipi=" + projetipi + "&text=" + searchText + "&page=" + currentPage;
        }else{
            return link+"search?text=" + searchText + "&page=" + currentPage;
        }
        
    }