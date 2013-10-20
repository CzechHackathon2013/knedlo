//
//  DetailViewController.m
//  Knedlo
//
//  Created by Martin Wenisch on 10/19/13.
//  Copyright (c) 2013 Ragtime. All rights reserved.
//

#import "DetailMenuViewController.h"
#import "DetailViewController.h"

@interface DetailViewController ()

@property (nonatomic, strong) DetailMenuViewController *detailMenu;

@end

@implementation DetailViewController

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
    
    self.contentView.layer.cornerRadius = 10.0;
    self.contentView.layer.shadowOffset = CGSizeMake(0.0, 1.0);
    self.contentView.layer.shadowColor = [[UIColor blackColor] CGColor];
    self.contentView.layer.shadowRadius = 1.0;
    self.contentView.layer.shadowOpacity = 0.4;
    
    FormatFilter *formatFiler = [[FormatFilter alloc] init];
    
    UIImage *iconImage;
    if ([self.article.source isEqualToString:@"super.cz"]) {
        iconImage = [UIImage imageNamed:@"supercz.png"];
    } else if ([self.article.source isEqualToString:@"blog.respekt.cz"]) {
        iconImage = [UIImage imageNamed:@"blogrespekt.png"];
    } else {
        iconImage = [UIImage imageNamed:@"moviezone.png"];
    }
    
    self.titleLabel.text = self.article.title;
    self.descriptionLabel.text = self.article.descriptionProperty;
    self.articleView.text = self.article.text;
    
    [formatFiler setWidth:iconImage.size.width height:iconImage.size.height];
    self.iconView.image = [formatFiler imageByFilteringImage:iconImage];
    
    //enable swipe back
    self.navigationController.interactivePopGestureRecognizer.delegate = (id<UIGestureRecognizerDelegate>)self;
    
    //add side menu
    UIStoryboard *storyboard = [UIStoryboard storyboardWithName:@"Knedlik_iPad" bundle:nil];
    self.detailMenu = [storyboard instantiateViewControllerWithIdentifier:@"DetailMenu"];
    self.detailMenu.noiseView = self.noiseView;
    [self.view addSubview:self.detailMenu.view];
    [self addChildViewController:self.detailMenu];
    self.detailMenu.view.translatesAutoresizingMaskIntoConstraints = NO;
    
    //left side distance (for moving)
    self.detailMenu.sideConstraint =[NSLayoutConstraint
                                   constraintWithItem:self.detailMenu.view
                                   attribute:NSLayoutAttributeLeading
                                   relatedBy:NSLayoutRelationEqual
                                   toItem:self.view
                                   attribute:NSLayoutAttributeTrailing
                                   multiplier:1.0
                                   constant:-85];
    [self.view addConstraint:self.detailMenu.sideConstraint];
    
    //pinned to top of superview
    NSLayoutConstraint * myConstraint = [NSLayoutConstraint
                                         constraintWithItem:self.detailMenu.view
                                         attribute:NSLayoutAttributeTop
                                         relatedBy:NSLayoutRelationEqual
                                         toItem:self.view
                                         attribute:NSLayoutAttributeTop
                                         multiplier:1.0
                                         constant:0];
    myConstraint.priority = 1000;
    [self.view addConstraint:myConstraint];
    
    //pinned to bottom of superview
    myConstraint =[NSLayoutConstraint
                   constraintWithItem:self.detailMenu.view
                   attribute:NSLayoutAttributeBottom
                   relatedBy:NSLayoutRelationEqual
                   toItem:self.bottomLayoutGuide
                   attribute:NSLayoutAttributeTop
                   multiplier:1.0
                   constant:0];
    myConstraint.priority = 1000;
    [self.view addConstraint:myConstraint];
    
    //fixed width
    myConstraint =[NSLayoutConstraint
                   constraintWithItem:self.detailMenu.view
                   attribute:NSLayoutAttributeWidth
                   relatedBy:NSLayoutRelationEqual
                   toItem:nil
                   attribute:NSLayoutAttributeNotAnAttribute
                   multiplier:1.0
                   constant:413];
    myConstraint.priority = 1000;
    [self.view addConstraint:myConstraint];
    
    [self.view layoutIfNeeded];
    
    //noise tap
    self.noiseView.userInteractionEnabled = YES;
    UITapGestureRecognizer *tapGesture = [[UITapGestureRecognizer alloc] initWithTarget:self action:@selector(noiseViewTaped:)];
    [self.noiseView addGestureRecognizer:tapGesture];
}

- (void)didReceiveMemoryWarning
{
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

- (IBAction)backButtonPressed:(id)sender
{
    [self.navigationController popToRootViewControllerAnimated:YES];
}

- (void)noiseViewTaped:(UITapGestureRecognizer *)recognizer
{
    [self.detailMenu hideAnimated:YES];
}

@end
