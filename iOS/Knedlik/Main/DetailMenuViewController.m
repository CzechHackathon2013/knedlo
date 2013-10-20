//
//  DetailMenuViewController.m
//  Knedlo
//
//  Created by Martin Wenisch on 10/20/13.
//  Copyright (c) 2013 Ragtime. All rights reserved.
//

#import "DetailMenuViewController.h"

@interface DetailMenuViewController ()

@property (nonatomic) BOOL menuVisible;
@property (nonatomic) CGPoint previousLocation;

@end

@implementation DetailMenuViewController

- (id)initWithNibName:(NSString *)nibNameOrNil bundle:(NSBundle *)nibBundleOrNil
{
    self = [super initWithNibName:nibNameOrNil bundle:nibBundleOrNil];
    if (self) {
        // Custom initialization
    }
    return self;
}

- (void)viewDidLoad
{
    [super viewDidLoad];
	// Do any additional setup after loading the view.
    
    UIPanGestureRecognizer *panGesture = [[UIPanGestureRecognizer alloc] initWithTarget:self action:@selector(panGestureMoved:)];
    panGesture.maximumNumberOfTouches = 1;
    panGesture.minimumNumberOfTouches = 1;
    [self.menuPull addGestureRecognizer:panGesture];
    self.menuPull.userInteractionEnabled = YES;
}

- (void)didReceiveMemoryWarning
{
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

- (void)panGestureMoved:(UIPanGestureRecognizer *)recognizer
{
    
    if (recognizer.state == UIGestureRecognizerStateBegan) {
        self.previousLocation = [recognizer locationInView:self.view.superview];
        NSLog(@"started location: %f", [recognizer locationInView:self.view.superview].x);
        self.noiseView.hidden = NO;
    } else if (recognizer.state == UIGestureRecognizerStateEnded || recognizer.state == UIGestureRecognizerStateCancelled) {
        
        if (!self.menuVisible && self.sideConstraint.constant < -180) {
            [self showAnimated:YES];
        } else if (!self.menuVisible && self.sideConstraint.constant >= -180) {
            [self hideAnimated:YES];
        } else if (self.menuVisible && self.sideConstraint.constant < -300) {
            [self showAnimated:YES];
        } else if (self.menuVisible && self.sideConstraint.constant >= -300) {
            [self hideAnimated:YES];
        }
        
        NSLog(@"ended location: %f", [recognizer locationInView:self.view.superview].x);
    } else if (recognizer.state == UIGestureRecognizerStateChanged) {
        NSLog(@"moved location: %f", [recognizer locationInView:self.view.superview].x);
        
        CGFloat diff = [recognizer locationInView:self.view.superview].x - self.previousLocation.x;
        if (self.sideConstraint.constant + diff < -410) {
            self.sideConstraint.constant = -410;
        } else if (self.sideConstraint.constant + diff > -85) {
            self.sideConstraint.constant = -85;
        } else {
            self.sideConstraint.constant = self.sideConstraint.constant + diff;
        }
        
        //update noise view alpha
        self.noiseView.alpha = ((85 + self.sideConstraint.constant) / -325);
        
        self.previousLocation = [recognizer locationInView:self.view.superview];
    }
    [self.view layoutIfNeeded];
}

- (void)showAnimated:(BOOL)animated
{
    self.sideConstraint.constant = -410;
    [UIView animateWithDuration:0.2
                     animations:^{
                         [self.view.superview layoutIfNeeded];
                         self.noiseView.alpha = 1.0;
                     } completion:^(BOOL finished) {
                         self.menuVisible = YES;
                     }];
}

- (void)hideAnimated:(BOOL)animated
{
    self.sideConstraint.constant = -85;
    [UIView animateWithDuration:0.2
                     animations:^{
                         [self.view.superview layoutIfNeeded];
                         self.noiseView.alpha = 0.0;
                     } completion:^(BOOL finished) {
                         self.menuVisible = NO;
                         self.noiseView.hidden = YES;
                     }];
}

@end
