//
//  ReaderCell.m
//  Knedlik
//
//  Created by Martin Wenisch on 10/19/13.
//  Copyright (c) 2013 Ragtime. All rights reserved.
//

#import "ReaderCell.h"
#import "CustomFlowLayout.h"
#import "GTLKnedlo.h"
#import "AppDelegate.h"

@interface ReaderCell ()

@property (nonatomic) CGPoint previousLocation;

@end

@implementation ReaderCell

- (id)initWithFrame:(CGRect)frame
{
    self = [super initWithFrame:frame];
    if (self) {
        // Initialization code
    }
    return self;
}

/*
// Only override drawRect: if you perform custom drawing.
// An empty implementation adversely affects performance during animation.
- (void)drawRect:(CGRect)rect
{
    // Drawing code
}
*/

- (void)panGestureMoved:(UIPanGestureRecognizer *)recognizer
{
    
    if (recognizer.state == UIGestureRecognizerStateBegan) {
        self.previousLocation = [recognizer locationInView:self];
        NSLog(@"started location: %f", [recognizer locationInView:self].x);
    } else if (recognizer.state == UIGestureRecognizerStateEnded || recognizer.state == UIGestureRecognizerStateCancelled) {
        NSLog(@"ended location: %f", [recognizer locationInView:self].x);
        NSIndexPath *indexPath = [self.collectionView indexPathForCell:self];
        
        if ([recognizer locationInView:self].x >= 0 && [recognizer locationInView:self].x <= 100) {
            //delete
            ((CustomFlowLayout *)self.collectionView.collectionViewLayout).leftDelete = YES;
            [self.dataSource removeObjectAtIndex:indexPath.row];
            [self.collectionView deleteItemsAtIndexPaths:@[indexPath]];
            [self deleteAction:(GTLKnedloArticle *)[self.dataSource objectAtIndex:indexPath.row]];
            
        } else {        
            [self hideTrashAnimated:YES];
        }
        
        if ([recognizer locationInView:self].x >= self.frame.size.width - 100 && [recognizer locationInView:self].x <= self.frame.size.width) {
            //add
            ((CustomFlowLayout *)self.collectionView.collectionViewLayout).leftDelete = NO;
            [self.dataSource removeObjectAtIndex:indexPath.row];
            [self.collectionView deleteItemsAtIndexPaths:@[indexPath]];
            [self readLaterAction:(GTLKnedloArticle *)[self.dataSource objectAtIndex:indexPath.row]];
        } else {
            [self hideTrashAnimated:YES];
        }
    } else if (recognizer.state == UIGestureRecognizerStateChanged) {
        NSLog(@"moved location: %f", [recognizer locationInView:self].x);
        
        CGFloat diff = [recognizer locationInView:self].x - self.previousLocation.x;
        self.contentViewLeadingSpace.constant = self.contentViewLeadingSpace.constant + diff;
        self.contentViewTrailingSpace.constant = self.contentViewTrailingSpace.constant - diff;
        self.trashViewLeadingSpace.constant = self.trashViewLeadingSpace.constant - diff;
        self.addViewTrailingSpace.constant = self.addViewTrailingSpace.constant + diff;
        
        if (self.trashViewLeadingSpace.constant > 0) {
            self.trashViewLeadingSpace.constant = 0;
        }
        
        if (self.addViewTrailingSpace.constant > 0) {
            self.addViewTrailingSpace.constant = 0;
        }
        
        [self setNeedsLayout];
        
        
        self.previousLocation = [recognizer locationInView:self];
    }
}

- (void)hideTrashAnimated:(BOOL)animated
{
    self.contentViewLeadingSpace.constant = 30;
    self.contentViewTrailingSpace.constant = 30;
    self.trashViewLeadingSpace.constant = -100;
    self.addViewTrailingSpace.constant = -100;
    
    if (animated) {
        [UIView animateWithDuration:0.1
                         animations:^{
                             [self layoutIfNeeded];
                         }
                         completion:^(BOOL finished) {
                             
                         }];
    }
}

- (BOOL)gestureRecognizerShouldBegin:(UIGestureRecognizer *)recognizer {
    if ([recognizer isKindOfClass:[UIPanGestureRecognizer class]]) {
        UIPanGestureRecognizer *panRecognizer = (UIPanGestureRecognizer *)recognizer;
        CGPoint velocity = [panRecognizer velocityInView:self];
        return ABS(velocity.x) > ABS(velocity.y); // Horizontal panning
        //return ABS(velocity.x) < ABS(velocity.y); // Vertical panning
    } else {
        return YES;
    }
}

- (void)deleteAction:(GTLKnedloArticle *)article
{
//    GTLQueryKnedlo *query = [GTLQueryKnedlo queryForActionWithAction:@"delete" articleLink:article.link];
//    GTLServiceKnedlo *service = ((AppDelegate *)[[UIApplication sharedApplication] delegate]).knedloService;
//    [service executeQuery:query completionHandler:^(GTLServiceTicket *ticket, GTLKnedloBadgeCollection *object, NSError *error) {
//        NSLog(@"response: %@", object);
//        
//        // Do something with items.
//    }];
}

- (void)readLaterAction:(GTLKnedloArticle *)article
{
//    GTLQueryKnedlo *query = [GTLQueryKnedlo queryForActionWithAction:@"save" articleLink:article.link];
//    GTLServiceKnedlo *service = ((AppDelegate *)[[UIApplication sharedApplication] delegate]).knedloService;
//    [service executeQuery:query completionHandler:^(GTLServiceTicket *ticket, GTLKnedloBadgeCollection *object, NSError *error) {
//        NSLog(@"response: %@", object);
//        
//        // Do something with items.
//    }];
}

@end
