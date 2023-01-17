// Akademik Veri


//alttaki fonksiyonun implemente edilmesi gerekiyor.
//function searchFromPageLink(currentPage)

//currentpage 0 ile başlar
	function getPageLinksContent(currentPage, totalPage){
		let result;
		if(totalPage > 0){
			
			result =  `<ul class="pagination justify-content-center">`;
			result += getFirstPageLinkContent(currentPage, totalPage);
			result += getPreviousLinkContent(currentPage, totalPage);
			
			let beginningPage = 0;
			let endPage = totalPage-1;
			if(totalPage > 9){
				beginningPage = currentPage - 4;
				endPage = currentPage + 4;
				if(beginningPage < 0){
					endPage -= beginningPage;
					beginningPage = 0;
				}
				if(endPage > totalPage-1){
					beginningPage -= (endPage - (totalPage-1))
					endPage = totalPage-1
				}
			}	
			let i;
			for(i = beginningPage; i <= endPage; i++){
				result += preparePageLink(currentPage, i);
			}
		
			result += getNextLinkContent(currentPage, totalPage);
			result += getLastPageLinkContent(currentPage, totalPage);
			result += `</ul>`;
			return result;
		}
		else{
			return ``;
		}
	}
	function getFirstPageLinkContent(currentPage, totalPage){
		
		let classValue = (totalPage > 1 && currentPage > 0) ? `page-item prev-page` : `page-item prev-page disabled`;
		return `<li class="` 
						+ classValue + 
						`"><a class="page-link"  href="#"  onclick= "searchFromPageLink(` 
						+ (0) + 
						`)" aria-label="Previous"> <i class="fa fa-angle-left"></i> <i class="fa fa-angle-left"></i><span
						class="sr-only">First</span>
				</a></li>`;
	}

	function getPreviousLinkContent(currentPage, totalPage){
	
		let classValue = (totalPage > 1 && currentPage > 0) ? `page-item prev-page` : `page-item prev-page disabled`;
		return `<li class="` 
						+ classValue + 
						`"><a class="page-link"  href="#"  onclick= "searchFromPageLink(` 
						+ (currentPage - 1) + 
						`)" aria-label="Previous"> <i class="fa fa-angle-left"></i> <span
						class="sr-only">Previous</span>
				</a></li>`;
	}

	function getNextLinkContent(currentPage, totalPage){
	
		let classValue = (totalPage > 1 && currentPage < totalPage-1) ? `page-item next-page` : `page-item next-page disabled`;
		return `<li class="` 
						+ classValue + 
						`"><a class="page-link"  href="#"  onclick= "searchFromPageLink(` 
						+ (currentPage + 1) + 
						`)" aria-label="Next"> <i class="fa fa-angle-right"></i> <span
						class="sr-only">Next</span>
				</a></li>`;
	}
	
	function getLastPageLinkContent(currentPage, totalPage){
	
		let classValue = (totalPage > 1 && currentPage < totalPage-1) ? `page-item next-page` : `page-item next-page disabled`;
		return `<li class="` 
						+ classValue + 
						`"><a class="page-link"  href="#"  onclick= "searchFromPageLink(` 
						+ (totalPage-1) + 
						`)" aria-label="Next"> <i class="fa fa-angle-right"></i><i class="fa fa-angle-right"></i> <span
						class="sr-only">Next</span>
				</a></li>`;
	}
	//currentpage ve thisPage 0 ile başlar
	function preparePageLink(currentPage, thisPage){
		let classValue = (currentPage == thisPage) ? `page-item active` : `page-item`;
		return `
				<li class="` 
						+ classValue + 
						`"><a class="page-link"  href="#"  onclick= "searchFromPageLink(` 
						+ (thisPage) + 
						`)" >
						` + (thisPage+1) + `
					</a></li>
				`;
	}