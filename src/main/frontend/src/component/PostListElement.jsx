import React from 'react';
import { Container, Row, Col, Image, Button } from 'react-bootstrap';
import HrefWrapper from './HrefWrapper';
import styled from 'styled-components';
import history from '../config/history';

const PostWrapper = styled.div`
    display: flex,
    padding:25px;
    border: 2px solid #e5e5e5;
    border-radius: 15px;
`

const ContentWrapper = styled.div`
    display: flex,
    padding: 10px;

`

const ContentColumn = styled(Col)`
&:hover {
    background-color: #f8f9fa;
    cursor: pointer;
}
`

const ImageWrapper = styled.div`
    display: flex;
    height: 100%;
    width: 100%;
    font-size: 1.2em;
    font-weight: bold;
    align-items: center;
    justify-content: center;
`

const TitleWrapper = styled.div`
    display: flex;
    padding: 16px 12px 16px 12px;
    height: 100%;
    width: 100%;
    font-size: 1.2em;
    font-weight: bold;
    &:hover {
        background-color: #f8f9fa;
        cursor: pointer;
    }
`

const FittedImage = styled(Image)`
    width: 100%;
    height: auto;
`

const ActionButtonWrapper = styled.div`
    display: flex;
    flex-direction: column;
    justify-content: space-evenly;
    border-left: 2px solid #e5e5e5;
    align-items: center;
    height: 100%;
    button > img {
        filter: invert(98%) sepia(44%) saturate(8%) hue-rotate(209deg) brightness(100%) contrast(103%);
    }
`

const PostFooterWrapper = styled.div`
    display: flex;
    width: 100%;
    flex-direction: row;
    justify-content: space-between;
`

const FullHeightRow = styled(Row)`
    height: 100%;
`

const PostInfoBarWrapper = styled.div`
    display: flex;
    flex-direction: column;
    width: 100%;
    height: 100%;
    justify-content: center;
    align-items: center;
    &:hover{
        cursor: pointer;
        filter: ${props => props.userAddLike ? "invert(27%) sepia(40%) saturate(3502%) hue-rotate(332deg) brightness(96%) contrast(91%);" : "invert(23%) sepia(79%) saturate(4294%) hue-rotate(117deg) brightness(98%) contrast(101%)"};
    }
`

function PostListElement({ postId, topic, content, author, creationDate, avatarUrl, editable, likeQuantity, openPost, openDeleteModal, openEditModal, addLike, deleteLike, userAddLike }) {
    return (
        <PostWrapper >
            <Container>
                <Row>
                    <Col xs={editable ? 10 : 12}>
                        <Row>
                            <Col xs={2}>
                                <ImageWrapper>
                                    <FittedImage src={avatarUrl} rounded />
                                </ImageWrapper>
                            </Col>
                            <Col xs={10}>
                                <Row>
                                    <ContentColumn xs={11} onClick={openPost}>
                                        <Row>
                                            <TitleWrapper>
                                                {topic}
                                            </TitleWrapper>
                                        </Row>
                                        <Row>
                                            <ContentWrapper>
                                                {content}
                                            </ContentWrapper>
                                        </Row>
                                    </ContentColumn>
                                    <Col xs={1}>
                                        <PostInfoBarWrapper onClick={() => userAddLike ? deleteLike(postId) : addLike(postId)} userAddLike={userAddLike}>
                                            {userAddLike ?
                                                <>
                                                    <span>{likeQuantity}</span>
                                                    <Image src={process.env.PUBLIC_URL + "/icons/down.svg"} width={25} />
                                                </>
                                                :
                                                <>
                                                    <Image src={process.env.PUBLIC_URL + "/icons/up.svg"} width={25} />
                                                    <span>{likeQuantity}</span>
                                                </>
                                            }
                                        </PostInfoBarWrapper>
                                    </Col>
                                </Row>
                                <hr />
                                <Row>
                                    <PostFooterWrapper>
                                        <HrefWrapper hrefAction={() => history.push(`/profile/${author}`)}>
                                            <p>Autor: {author}</p>
                                        </HrefWrapper>
                                        <p>Data utworzenia: {creationDate}</p>
                                    </PostFooterWrapper>
                                </Row>
                            </Col>
                        </Row>
                    </Col>
                    {editable ?
                        <Col xs={2}>
                            <FullHeightRow>
                                <ActionButtonWrapper>
                                    <Button variant={'danger'} onClick={() => openDeleteModal(postId)}>
                                        <Image src={process.env.PUBLIC_URL + "/icons/trash.svg"} width={20} />
                                    </Button>
                                    <Button variant={'primary'} onClick={() => openEditModal({ postId, topic, content, author, avatarUrl, creationDate, editable })}>
                                        <Image src={process.env.PUBLIC_URL + "/icons/edit.svg"} width={20} />
                                    </Button>
                                </ActionButtonWrapper>
                            </FullHeightRow>
                            <Row>
                            </Row>
                        </Col>
                        :
                        <></>
                    }

                </Row>
            </Container >
        </PostWrapper >
    )
}

export default PostListElement;