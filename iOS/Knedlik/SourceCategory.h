//
//  SourceCategory.h
//  Knedlo
//
//  Created by Martin Wenisch on 10/20/13.
//  Copyright (c) 2013 Ragtime. All rights reserved.
//

#import <Foundation/Foundation.h>

@interface SourceCategory : NSObject

@property (nonatomic, strong) NSString *categoryName;
@property (nonatomic, strong) UIImage *categoryImage;

- (id)initWithSource:(NSString *)name image:(UIImage *)image;

@end
