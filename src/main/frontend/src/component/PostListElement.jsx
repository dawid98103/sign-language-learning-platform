import React from 'react';
import { Container, Row, Col, Image } from 'react-bootstrap';
import styled from 'styled-components';

const PostWrapper = styled.div`
    display: flex,
    padding:25px;
    border: 2px solid #e5e5e5;
    border-radius: 15px;
    &:hover {
        background-color: #f8f9fa;
        cursor: pointer;
    }
`

const ContentWrapper = styled.div`
    display: flex,
    padding: 10px;
`

const TitleWrapper = styled.div`
    display: flex,
    padding: 10px;
    font-size: 1.2em;
    font-weight: bold;
`

function PostListElement(){
    return (
        <PostWrapper onClick={() => console.log("Enter POST")}>
            <Container>
                <Row>
                    <Col xs={2}>
                        <TitleWrapper>
                            <Image src="https://i.pinimg.com/280x280_RS/3a/65/d6/3a65d69aa6ca638c7575f6b883d71900.jpg" rounded width={100} height={100}/>
                        </TitleWrapper>        
                    </Col>
                    <Col xs={10}>
                        <Row>
                            <TitleWrapper>
                                Title
                            </TitleWrapper>        
                        </Row>
                        <Row>
                            <ContentWrapper>
                                Lorem ipsum dolor sit amet, consectetur adipiscing elit. 
                                Lorem ipsum dolor sit amet, consectetur adipiscing elit. 
                            </ContentWrapper>
                        </Row>
                        <Row>
                            <p>Autor: Dawid Kobylarz</p>
                        </Row>
                    </Col>
                </Row>
            </Container>
        </PostWrapper>
    )
}

export default PostListElement;