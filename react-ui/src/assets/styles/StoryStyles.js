import styled from 'styled-components';
export const StoryWrapper = styled.section`
 padding-top: 10px;
 margin-bottom: 10px;
 border-top: 1px solid #cccccc;

 &:first-of-type {
   border-top: 0;
 }
 
 &:last-of-type {
   margin-bottom: 0;
   padding-bottom: 0;
 }
`;

export const StoryTitle = styled.h1`
 margin: 0 0 0 0;
 padding: 0 0 0 0;
 font-size: 18px;
 line-height: 1.8;
 text-decoration: none;
 
 display: flex;
 justify-content: space-between;
 
 a {
   color: #0000f9;
   text-decoration: none;
   background-color: unset;
   padding: 5px 0;
   border-radius: 4px;
 }
`;

export const StoryMeta = styled.div`
 font-style: italic;
 
 > span:not(:first-child):before {
   content: '';
   margin: 0 7px;
 }
 
 .story__meta-bold {
   font-weight: bold;
 }
`;

export const StoryMetaElement = styled.span`
 font-weight: bold;
 color: ${(props) => props.color || 'red'};
`;
